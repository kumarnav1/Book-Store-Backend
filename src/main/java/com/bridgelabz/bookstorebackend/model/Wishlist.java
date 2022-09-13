package com.bridgelabz.bookstorebackend.model;


import lombok.Data;

import javax.persistence.*;
import java.awt.print.Book;

@Data
@Entity
public class Wishlist {

    @Id
    @GeneratedValue
    private Integer wishlistId;

    @ManyToOne
    @JoinColumn(name="userId")
    private UserRegistration user;
    @ManyToOne
    @JoinColumn(name="bookId")
    private BookData book;

    public Wishlist() {
    }


    public Wishlist(UserRegistration user, BookData book) {
        super();
        this.user = user;
        this.book = book;
    }


}
