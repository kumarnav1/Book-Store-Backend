package com.bridgelabz.bookstorebackend.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class DeliveryDTO {

    public String firstName;

    public String lastName;

    @Email
    private String email;

    @NotEmpty(message = "address can not be empty")
    private String address;
}
