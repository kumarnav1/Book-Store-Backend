package com.bridgelabz.bookstorebackend.repository;

import com.bridgelabz.bookstorebackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT * FROM book_store_database.order;", nativeQuery = true)
    List<Order> listOrder();
}
