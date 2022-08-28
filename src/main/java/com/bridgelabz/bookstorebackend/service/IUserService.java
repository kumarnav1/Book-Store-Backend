package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.UserRegistrationDTO;
import com.bridgelabz.bookstorebackend.model.UserRegistration;

public interface IUserService {

    UserRegistration registerUser(UserRegistrationDTO userDTO);

    Boolean verifyOtp(String email, Integer otp);

    String loginUser(String email, String password);

    String getToken(String email);

    UserRegistration getUserDataByToken(String token);

    UserRegistration updateRecordByToken(String token, UserRegistrationDTO userDTO);

    String deleteRecordByToken(String token);

    UserRegistration getUserDataById(int userId);

    String forgotPassword(String email, String password);

    UserRegistration getUserDataByEmail(String email);
}
