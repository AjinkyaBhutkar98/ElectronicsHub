package com.ajinkyabhutkar.electronicstore.repositories;

import com.ajinkyabhutkar.electronicstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    Optional<List<Product>> findByTitleContaining(String title);

    Optional<List<Product>> findByIsLiveTrue();

}
