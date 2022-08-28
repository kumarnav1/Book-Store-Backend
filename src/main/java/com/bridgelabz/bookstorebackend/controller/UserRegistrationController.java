package com.bridgelabz.bookstorebackend.controller;


import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.dto.UserLoginDTO;
import com.bridgelabz.bookstorebackend.dto.UserOtpVerificationDTO;
import com.bridgelabz.bookstorebackend.dto.UserRegistrationDTO;
import com.bridgelabz.bookstorebackend.model.UserRegistration;
import com.bridgelabz.bookstorebackend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {

    @Autowired
    IUserService userRegistrationService;

    final static String SUCCESS_MESSAGE = "RegistrationEntered Otp is valid, and Registration was successful.";
    final static String FAIL_MESSAGE = "RegistrationEntered OTP was not valid!, Registration failed!, please try again";

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addUserInBookStore(@Valid @RequestBody UserRegistrationDTO userDTO) {
        UserRegistration userRegistration = userRegistrationService.registerUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Registered Successfully", userRegistration);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updatebytoken/{token}")
    public ResponseEntity<ResponseDTO> updateRecordById(@PathVariable String token, @Valid @RequestBody UserRegistrationDTO userDTO) {
        UserRegistration entity = userRegistrationService.updateRecordByToken(token, userDTO);
        ResponseDTO dto = new ResponseDTO("User Record updated successfully", entity);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = {"/get/{token}"})
    public ResponseEntity<ResponseDTO> getUserDetailsByToken(@PathVariable String token) {
        UserRegistration userDataByToken = userRegistrationService.getUserDataByToken(token);
        ResponseDTO responseDTO = new ResponseDTO("Success Call for Person Id!!!", userDataByToken);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping({"/verifyotp"})
    public String verifyOtp(@Valid @RequestBody UserOtpVerificationDTO userNameOtpDTO) {
        String email = userNameOtpDTO.getEmail();
        Integer otp = userNameOtpDTO.getOtp();
        Boolean isVerifyOtp = userRegistrationService.verifyOtp(email, otp);
        if (!isVerifyOtp) return FAIL_MESSAGE;
        return SUCCESS_MESSAGE;
    }

    @GetMapping("/login")
    public String userLogin(@RequestParam String email, @RequestParam String password) {
        UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);
        String response = userRegistrationService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return response;
    }

    @GetMapping(value = {"/delete/{token}"})
    public String deleteUserDetailsByToken(@PathVariable String token) {
        return userRegistrationService.deleteRecordByToken(token);
    }

    @PostMapping("/forgotpassword")
    public String forgotPassword(@RequestParam String email, @RequestParam String password) {
        return userRegistrationService.forgotPassword(email, password);
    }
}