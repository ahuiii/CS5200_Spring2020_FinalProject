package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {

  User findUserById(int id);

  User findUserByUserName(String userName);

  User findUserByEmail(String email);

}
