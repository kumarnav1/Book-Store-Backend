package com.bridgelabz.bookstorebackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class UserRegistrationDTO {

    @Pattern(regexp = "^[A-Z]{1}[A-Za-z]{1,}$", message = "Invalid First Name")
    public String firstName;

    @Pattern(regexp = "^[A-Z]{1}[A-Za-z]{1,}$", message = "Invalid Last Name")
    public String lastName;

    @NotEmpty(message = "Social Security Number cannot be null")
    public String socialSecurityNumber;

    @Pattern(regexp = "[a-zA-Z0-9][a-zA-Z-0-9_+]*([.][a-zA-Z0-9]+)?[@][a-zA-Z-0-9]+[.][a-z]{2,4}([.][a-zA-Z]{2,4})?", message = "Invalid email Name")
    public String email;

    @NotEmpty(message = "password cannot be null")
    public String password;

    @JsonFormat(pattern="dd MMM yyyy")
    public LocalDate dateOfBirth;

    @JsonFormat(pattern="dd MMM yyyy")
    public LocalDate registrationDate;
}
