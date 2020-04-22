package edu.northeastern.cs5200.web;

import com.google.common.collect.ImmutableMap;
import edu.northeastern.cs5200.entities.User;
import edu.northeastern.cs5200.services.SecurityFilter;
import edu.northeastern.cs5200.services.UserService;
import edu.northeastern.cs5200.web.dto.ImmutableLoginResponse;
import edu.northeastern.cs5200.web.dto.LoginResponse;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final Argon2PasswordEncoder argon2PasswordEncoder;
  private final SecurityFilter securityFilter;
  private final UserService userService;
  private final Key secretKey;

  public UserController(
      Argon2PasswordEncoder argon2PasswordEncoder, SecurityFilter securityFilter,
      UserService userService, Key secretKey) {
    this.argon2PasswordEncoder = argon2PasswordEncoder;
    this.securityFilter = securityFilter;
    this.userService = userService;
    this.secretKey = secretKey;
  }

  @PostMapping(value = "/login", produces = "application/json")
  public ResponseEntity<LoginResponse> LoginController(@RequestBody User user) {
    User compUser = userService.findByEmail(user.getUserName());
    try {
      boolean match = argon2PasswordEncoder.matches(user.getPassword(),
          compUser.getPassword());
      if (match) {
        String token = Jwts.builder()
            .setIssuer("edu.northeastern.cs5200")
            .setClaims(ImmutableMap.of(
                "role", compUser.getUserRole(),
                "id", compUser.getId()
            ))
            .signWith(secretKey)
            .compact();
        return ResponseEntity.ok(ImmutableLoginResponse.builder()
            .token(token)
            .userId(String.valueOf(compUser.getId()))
            .role(compUser.getUserRole())
            .build());
      }
      throw new HttpClientErrorException(HttpStatus.valueOf(401));
    } catch (NullPointerException | HttpClientErrorException e) {
      return ResponseEntity.status(401).build();
    }

  }

  @PostMapping(value = "/register", produces = "application/json")
  public ResponseEntity<LoginResponse> signUpController(@RequestBody User user) {
    User newUser = userService.signUp(user);
    String token = Jwts.builder()
        .setIssuer("edu.northeastern.cs5200")
        .setClaims(ImmutableMap.of(
            "role", newUser.getUserRole(),
            "id", newUser.getId()
        ))
        .signWith(secretKey)
        .compact();
    return ResponseEntity.ok(ImmutableLoginResponse.builder()
        .token(token)
        .userId(String.valueOf(newUser.getId()))
        .role(newUser.getUserRole())
        .build());
  }

  @GetMapping(value = "/all", produces = "application/json")
  public ResponseEntity<List<User>> getAllUser(@RequestHeader String token) {
    try {
      String userRole = securityFilter.checkUserRole(token);
      if (userRole.equals("admin")) {
        return ResponseEntity.ok(userService.getAllUsers().stream()
            .peek((user) -> user.setPassword(""))
            .collect(Collectors.toList()));
      }
    } catch (Exception e) {
      return ResponseEntity.status(401).build();
    }
    return ResponseEntity.status(401).build();
  }

  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<User> getUser(@RequestHeader String token,
      @PathVariable String id) {
    try {
      String userId = securityFilter.checkUserId(token);
      String userRole = securityFilter.checkUserRole(token);
      if (userId.equals(id) || userRole.equals("admin")) {
        User user = userService.findById(id);
        user.setPassword("");
        return ResponseEntity.ok(user);
      }
    } catch (Exception e) {
      return ResponseEntity.status(401).build();
    }
    return ResponseEntity.status(401).build();
  }

  @PutMapping(value = "/update", produces = "application/json")
  public ResponseEntity<User> updateUser(@RequestHeader String token,
      @RequestBody User user) {
    try {
      String userId = securityFilter.checkUserId(token);
      String userRole = securityFilter.checkUserRole(token);
      if (user.getId() == Integer.parseInt(userId) || userRole.equals("admin")) {
        User updateUser = userService.updateUser(user);
        updateUser.setPassword("");
        return ResponseEntity.ok(updateUser);
      }
    } catch (Exception e) {
      return ResponseEntity.status(401).build();
    }
    return ResponseEntity.status(401).build();
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@RequestHeader String token,
      @PathVariable String id) {
    try {
      String userRole = securityFilter.checkUserRole(token);
      if (userRole.equals("admin")) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
      }
    } catch (Exception e) {
      return ResponseEntity.status(401).build();
    }
    return ResponseEntity.status(401).build();
  }
}
