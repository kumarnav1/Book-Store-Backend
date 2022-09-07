package com.bridgelabz.bookstorebackend.repository;

import com.bridgelabz.bookstorebackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreCartRepository extends JpaRepository<Cart, Integer> {
}
