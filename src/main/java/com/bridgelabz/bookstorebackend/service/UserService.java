package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.UserRegistrationDTO;
import com.bridgelabz.bookstorebackend.model.UserRegistration;
import com.bridgelabz.bookstorebackend.repository.IUserRegistrationRepository;
import com.bridgelabz.bookstorebackend.service.serviceInterface.IUserService;
import com.bridgelabz.bookstorebackend.util.EmailSenderUtility;
import com.bridgelabz.bookstorebackend.util.TokenUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService implements IUserService {

    @Autowired
    EmailSenderUtility emailService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUserRegistrationRepository iUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    TokenUtility util;

    @Override
    public Integer registerUser(UserRegistrationDTO userDTO) {
        UserRegistration byEmailId = iUserRepository.findByEmailId(userDTO.getEmail());
        if (!(byEmailId == null))
            return 1;
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserRegistration user = modelMapper.map(userDTO, UserRegistration.class);
        String otp = getRandomNumberString();
        Integer intOtp = Integer.parseInt(otp);
        user.setOtp(intOtp);
        iUserRepository.save(user);
       emailService.sendEmail(user.getEmail(), "Otp for Verification", "Otp sent for verification purpose"
                + user.getFirstName() + "Please Click here to verify the Otp :-   "
                + "http://localhost:8080/user/verifyOtp" + intOtp);
        return 0;
    }

    @Override
    public int verifyOtp(String email, Integer otp) {
        UserRegistration user = iUserRepository.findByEmailId(email);
        if (user == null)
            return 0;
        Integer userOtpFromServer = user.getOtp();
        if (!(otp.equals(userOtpFromServer)))
            return 2;
        iUserRepository.changeVerified(email);
       emailService.sendEmail(user.getEmail(), "Verification Successful", "Hi " + user.getFirstName() + ", You have successfully " +
                "verified your account. You can now login using Your email and password using this link. " + "http://localhost:8080/user/login");
        return 1;
    }

    @Override
    public String loginUser(String email, String password) {
        UserRegistration userLogin = iUserRepository.findByEmailId(email);

        if (userLogin == null)
            return "User not found";
        if (userLogin.getVerified() == null)
            return "User not verified. First do the verification and try again. ";
        if (!(passwordEncoder.matches(password, userLogin.getPassword())))
            return "User name or password incorrect";
        return "User Login successfully and token is " + getToken(userLogin.getEmail());
    }

    public static String getRandomNumberString() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    @Override
    public String getToken(String email) {
        UserRegistration userRegistration = iUserRepository.findByEmailId(email);
        System.out.println(userRegistration);
        return util.createToken(userRegistration.getUserId());
    }

    @Override
    public UserRegistration getUserDataByToken(String token) {
        int id = util.decodeToken(token);
        return this.getUserDataById(id);
    }

    public UserRegistration updateRecordByToken(String token, UserRegistrationDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        int id = util.decodeToken(token);
        UserRegistration user = this.getUserDataById(id);
        modelMapper.map(userDTO, user);
        iUserRepository.save(user);
        return user;
    }

    @Override
    public String deleteRecordByToken(String token) {
        int userId = util.decodeToken(token);
        UserRegistration user = this.getUserDataById(userId);
        iUserRepository.delete(user);
        return "Data Deleted";
    }

    @Override
    public UserRegistration getUserDataById(int userId) {
        return iUserRepository.findByUserId(userId);
    }

    @Override
    public int forgotPassword(String email, String password) {
        UserRegistration userDataByEmail = this.getUserDataByEmail(email);
        if (userDataByEmail == null)
            return 0;
        String newPassword = passwordEncoder.encode(password);
        System.out.println("newPassword: " + newPassword);
        iUserRepository.updateNewPassword(email, newPassword);
        return 1;
    }

    @Override
    public UserRegistration getUserDataByEmail(String email) {
        return iUserRepository.findByEmailId(email);
    }

    @Override
    public int loginUserTest(String email, String password) {
        UserRegistration userLogin = iUserRepository.findByEmailId(email);
        if (userLogin == null)
            return 0;
        if (userLogin.getVerified() == null)
            return 1;
        if (!(passwordEncoder.matches(password, userLogin.getPassword())))
            return 2;
        return 3;
    }

    @Override
    public Object getIdByToken(String token) {
        return util.decodeToken(token);
    }
}