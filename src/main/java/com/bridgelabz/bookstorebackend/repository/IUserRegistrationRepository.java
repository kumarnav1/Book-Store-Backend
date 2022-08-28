package com.bridgelabz.bookstorebackend.repository;

import com.bridgelabz.bookstorebackend.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IUserRegistrationRepository extends JpaRepository<UserRegistration, Integer> {

    public UserRegistration findByUserId(int userId);

    @Modifying
    @Transactional
    @Query(value = "update user_registration set verified=true where email = :email ", nativeQuery = true)
    void changeVerified(String email);

    @Query(value = "SELECT * FROM user_registration where email=:email_Id", nativeQuery = true)
    public UserRegistration findByEmailId(String email_Id);

    @Modifying
    @Transactional
    @Query(value = "update user_registration set password = :newPassword where email = :email", nativeQuery = true)
    void updateNewPassword(String email, String newPassword);
}
