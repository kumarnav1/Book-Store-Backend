package com.bridgelabz.bookstorebackend.controller;


import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.dto.UserLoginDTO;
import com.bridgelabz.bookstorebackend.dto.UserRegistrationDTO;
import com.bridgelabz.bookstorebackend.model.UserRegistration;
import com.bridgelabz.bookstorebackend.service.serviceInterface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserRegistrationController {

    @Autowired
    IUserService userRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addUserInBookStore(@Valid @RequestBody UserRegistrationDTO userDTO) {
        Integer userRegistration = userRegistrationService.registerUser(userDTO);
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

    @GetMapping({"/verifyotp"})
    public int verifyOtp(@RequestParam String email , @RequestParam Integer otp) {
        return userRegistrationService.verifyOtp(email, otp);
    }

    @GetMapping("/login")
    public String userLogin(@RequestParam String email, @RequestParam String password) {
        UserLoginDTO userLoginDTO = new UserLoginDTO(email, password);
        return userRegistrationService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    }

    @GetMapping("/logintest")
    public int userTestLogin(@RequestParam String email, @RequestParam String password) {
        return userRegistrationService.loginUserTest(email, password);
    }

    @GetMapping(value = {"/delete/{token}"})
    public String deleteUserDetailsByToken(@PathVariable String token) {
        return userRegistrationService.deleteRecordByToken(token);
    }

    @GetMapping("/forgotpassword")
    public int forgotPassword(@RequestParam String email, @RequestParam String password) {
        return userRegistrationService.forgotPassword(email, password);
    }

    @GetMapping("/gettoken/{email}")
    public ResponseEntity<ResponseDTO> getToken(@PathVariable String email) {
        String token = userRegistrationService.getToken(email);
        ResponseDTO responseDTO = new ResponseDTO("token Sent", token);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getIdByToken/{token}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable String token) {
        return new ResponseEntity<>(new ResponseDTO("Get User Data By Id",
                userRegistrationService.getIdByToken(token)), HttpStatus.OK);
    }
}