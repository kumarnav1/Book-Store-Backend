package com.bridgelabz.bookstorebackend.dto;

import lombok.Data;

@Data
public class UserOtpVerificationDTO {
    private String email;
    private Integer otp;
}
