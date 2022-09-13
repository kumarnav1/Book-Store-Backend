package com.bridgelabz.bookstorebackend.service;


import com.bridgelabz.bookstorebackend.dto.WishListDTO;
import com.bridgelabz.bookstorebackend.model.BookData;
import com.bridgelabz.bookstorebackend.model.UserRegistration;
import com.bridgelabz.bookstorebackend.model.Wishlist;
import com.bridgelabz.bookstorebackend.repository.IBookRepository;
import com.bridgelabz.bookstorebackend.repository.IUserRegistrationRepository;
import com.bridgelabz.bookstorebackend.repository.IWishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private IWishListRepository wishlistRepo;

    @Autowired
    private IUserRegistrationRepository userRepo;

    @Autowired
    private IBookRepository bookRepo;

    public Wishlist addToWishlist(WishListDTO dto) {
        Optional<UserRegistration> user = userRepo.findById(dto.getUserId());
        Optional<BookData> book = bookRepo.findById(dto.getBookId());
        if (user.isPresent() && book.isPresent()) {
            Wishlist newWishList = new Wishlist(user.get(), book.get());
            wishlistRepo.save(newWishList);
            return newWishList;
        } else {
            return null;//book dosent exist
        }
    }
    public List<Wishlist> getAllWishlists() {
        List<Wishlist> list = wishlistRepo.findAll();
        return list;
    }
    public List<Wishlist> getWishlistRecordById(Integer id) {
        List<Wishlist> list = wishlistRepo.findByWishlistId(id);
        if (list.isEmpty()) {
            return null;// Wishlist doesn't exists for given id
        } else {
            return list;
        }
    }

    public List<Wishlist> getWishlistRecordByBookId(Integer bookId) {
        List<Wishlist> list = wishlistRepo.findByBookId(bookId);
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    public List<Wishlist> getWishlistRecordByUserId(Integer userId) {
        List<Wishlist> list = wishlistRepo.findByUserId(userId);
        return list;
    }

    public Wishlist deleteWishlistRecordById(Integer Id) {
        Optional<Wishlist> list = wishlistRepo.findById(Id);
        wishlistRepo.deleteById(Id);
        return list.get();
    }
}