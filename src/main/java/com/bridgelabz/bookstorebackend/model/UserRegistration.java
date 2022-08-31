package com.bridgelabz.bookstorebackend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class UserRegistration {

    @Id
    @GeneratedValue
    private Integer userId;

    private String firstName;

    private String lastName;

    private Boolean verified;

    private Integer otp;

    private String socialSecurityNumber;

    private String email;

    private String password;

    private LocalDate dateOfBirth;

    private LocalDate registrationDate;
}
