package com.bridgelabz.bookstorebackend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Delivery {

    @Id
    @GeneratedValue
    private Integer personId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

}