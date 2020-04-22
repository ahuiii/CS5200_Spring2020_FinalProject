package edu.northeastern.cs5200.services;

import edu.northeastern.cs5200.entities.User;
import edu.northeastern.cs5200.repository.UserRepository;
import java.util.List;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserRepository userRepository;

  private final Argon2PasswordEncoder argon2PasswordEncoder;

  public UserService(UserRepository userRepository,
      Argon2PasswordEncoder argon2PasswordEncoder) {
    this.userRepository = userRepository;
    this.argon2PasswordEncoder = argon2PasswordEncoder;
  }

  @Transactional
  public User signUp(User user) {
    User newUser = new User();
    newUser.setEmail(user.getUserName());
    newUser.setFullName(user.getFullName());
    newUser.setUserName(user.getUserName());
    newUser.setUserRole(user.getUserRole());
    newUser.setPassword(argon2PasswordEncoder.encode(user.getPassword()));
    return userRepository.save(newUser);
  }

  @Transactional
  public User updateUser(User user) {
    user.setEmail(user.getUserName());
    if (!user.getPassword().isEmpty()) {
      user.setPassword(argon2PasswordEncoder.encode(user.getPassword()));
    } else {
      User oldUser = findByEmail(user.getUserName());
      user.setPassword(oldUser.getPassword());
    }
    return userRepository.save(user);
  }

  @Transactional
  public void deleteUserById(String userId) {
    userRepository.deleteById(Integer.parseInt(userId));
  }

  public User findByUserName(String name) {
    return userRepository.findUserByUserName(name);
  }

  public User findByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }


  public List<User> getAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  public User findById(String id) {
    return userRepository.findUserById(Integer.parseInt(id));
  }
  
  public void deleteUserByUserName(String userName) {
    User user = userRepository.findUserByUserName(userName);
    userRepository.delete(user);
  }
  
  public User createUser(User user) {
	  
	  return userRepository.save(user);
  }
  public User updateByUserName(User user) {

	  return userRepository.save(user);
  }

  
}

