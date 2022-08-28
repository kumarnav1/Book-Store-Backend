package com.bridgelabz.bookstorebackend.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserLoginDTO {
    @Pattern(regexp = "[a-zA-Z0-9][a-zA-Z-0-9_+]*([.][a-zA-Z0-9]+)?[@][a-zA-Z-0-9]+[.][a-z]{2,4}([.][a-zA-Z]{2,4})?", message = "Invalid email address")
    private String email;
    @NotEmpty(message = "Password cant be null")
    private String password;
    public UserLoginDTO(String email,String password){
        this.email=email;
        this.password=password;
    }
}
