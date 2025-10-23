package com.ajinkyabhutkar.electronicstore.repositories;

import com.ajinkyabhutkar.electronicstore.entities.Cart;
import com.ajinkyabhutkar.electronicstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUser(User user);
}
