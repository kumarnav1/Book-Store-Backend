package com.bridgelabz.bookstorebackend.service.serviceInterface;

import com.bridgelabz.bookstorebackend.dto.UserRegistrationDTO;
import com.bridgelabz.bookstorebackend.model.UserRegistration;

public interface IUserService {

    Integer registerUser(UserRegistrationDTO userDTO);

    int verifyOtp(String email, Integer otp);

    String loginUser(String email, String password);

    String getToken(String email);

    UserRegistration getUserDataByToken(String token);

    UserRegistration updateRecordByToken(String token, UserRegistrationDTO userDTO);

    String deleteRecordByToken(String token);

    UserRegistration getUserDataById(int userId);

    int forgotPassword(String email, String password);

    UserRegistration getUserDataByEmail(String email);

    int loginUserTest(String email, String password);

    Object getIdByToken(String token);
}
