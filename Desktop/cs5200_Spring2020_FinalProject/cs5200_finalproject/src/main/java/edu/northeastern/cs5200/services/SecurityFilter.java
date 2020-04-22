package edu.northeastern.cs5200.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import org.springframework.stereotype.Service;

@Service
public class SecurityFilter {

  private final Key secretKey;

  public SecurityFilter(Key secretKey) {
    this.secretKey = secretKey;
  }

  public String checkUserRole(String token) throws SignatureException {
    Jws<Claims> claim = Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token);
    return (String) claim.getBody().get("role");
  }

  public String checkUserId(String token) throws SignatureException {
    Jws<Claims> claim = Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token);
    return String.valueOf(claim.getBody().get("id"));
  }

}
