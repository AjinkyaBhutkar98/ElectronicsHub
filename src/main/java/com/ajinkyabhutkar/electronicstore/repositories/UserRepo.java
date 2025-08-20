package com.ajinkyabhutkar.electronicstore.repositories;

import com.ajinkyabhutkar.electronicstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    //custom finder method
    Optional<User> findByEmail(String email);

    Optional<List<User>> findByNameContaining(String nameKeywords);

    Optional<User> findByEmailAndPassword(String email,String password);


}
