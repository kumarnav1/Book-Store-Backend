package com.bridgelabz.bookstorebackend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class BookData {
    @Id
    @GeneratedValue
    private Integer bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImage;
    private Integer price;
    private Integer quantity;
}
