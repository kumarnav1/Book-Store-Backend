package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.CartDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.model.BookData;
import com.bridgelabz.bookstorebackend.model.Cart;
import com.bridgelabz.bookstorebackend.model.UserRegistration;
import com.bridgelabz.bookstorebackend.repository.BookStoreCartRepository;
import com.bridgelabz.bookstorebackend.repository.IBookRepository;
import com.bridgelabz.bookstorebackend.repository.IUserRegistrationRepository;
import com.bridgelabz.bookstorebackend.service.serviceInterface.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService {

    @Autowired
    IBookRepository bookStoreRepository;

    @Autowired
    IUserRegistrationRepository userRegistrationRepository;

    @Autowired
    BookStoreCartRepository bookStoreCartRepository;

    @Override
    public ResponseDTO getCartDetails() {
        List<Cart> getCartDetails = bookStoreCartRepository.findAll();
        ResponseDTO dto = new ResponseDTO();
        if (getCartDetails.isEmpty()) {
            String message = " the cart is empty!!!";
            dto.setMessage(message);
            dto.setData(0);
            return dto;
        } else {
            dto.setMessage("the list of cart items is sucussfully retrived");
            dto.setData(getCartDetails);
            return dto;
        }
    }


    @Override
    public Optional<Cart> getCartDetailsById(Integer cartId) {
        Optional<Cart> getCartData = bookStoreCartRepository.findById(cartId);
        if (getCartData.isPresent()) {
            return getCartData;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cart> deleteCartItemById(Integer cartId) {
        Optional<Cart> deleteData = bookStoreCartRepository.findById(cartId);
        if (deleteData.isPresent()) {
            bookStoreCartRepository.deleteById(cartId);
            return deleteData;
        }
        return Optional.empty();
    }

    @Override
    public Cart updateRecordById(Integer cartId, CartDTO cartDTO) {
        Optional<Cart> cart = bookStoreCartRepository.findById(cartId);
        Optional<BookData> book = bookStoreRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRegistrationRepository.findById(cartDTO.getUserId());
        if (cart.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
                Cart newCart = new Cart(cartId, cartDTO.getQuantity(), book.get(), user.get());
                if (cartDTO.getQuantity() <= newCart.getQuantity()) {
                    book.get().setQuantity((book.get().getQuantity()) - (cartDTO.getQuantity()));
                    bookStoreRepository.save(book.get());
                    bookStoreCartRepository.save(newCart);
                    return newCart;
                }
                return null;// when quantity more
            }
            return null;//book or user is not present
        }
        return null;// cart is not there
    }


  /*  @Override
    public Cart updateQuantity(Integer id, Integer currentRequiredQuantity) {
        Optional<Cart> cart = bookStoreCartRepository.findById(id);
        if (cart.isPresent()) {
            Optional<BookData> book = bookStoreRepository.findById(cart.get().getBook().getBookId());
            Integer presentCartQuantity = cart.get().getQuantity();
            int effectiveChange = currentRequiredQuantity - presentCartQuantity;
            int changedUpdateQuantity = book.get().getQuantity() - effectiveChange;
            if (changedUpdateQuantity < 0)
                return null;//no sufficient quantity.// book is out of stock.// the req user quantity is negative or less than equal to zero
            cart.get().setQuantity(currentRequiredQuantity);
            bookStoreCartRepository.save(cart.get());
            book.get().setQuantity(changedUpdateQuantity);
            bookStoreRepository.save(book.get());
            return cart.get();
        }
        return null;
    }*/

    @Override
    public Cart updateQuantity(Integer id, Integer quantity) {
        Optional<Cart> cart = bookStoreCartRepository.findById(id);

        if (cart.isPresent()) {
            Optional<BookData> book = bookStoreRepository.findById(cart.get().getBook().getBookId());
            System.out.println("UPDATE HITTING ");
           /* if((book.get().getQuantity()) <0)
                return null;*/

            if ((book.get().getQuantity()) < 0) {
                book.get().setQuantity(0);
                bookStoreRepository.save(book.get());
                return null;
            }
//            if(quantity < book.get().getQuantity()) {
            if ((book.get().getQuantity()) >= 0) {



                book.get().setQuantity(book.get().getQuantity() - (quantity - cart.get().getQuantity()));
                cart.get().setQuantity(quantity);
                bookStoreCartRepository.save(cart.get());
                bookStoreRepository.save(book.get());
                return cart.get();



            } else {
                return null;//No sufficient quantity
            }
        } else {
            return null;//cart dosent exist
        }
    }

    @Override
    public Optional<Cart> deleteCartItemByIdAndQuantity(Integer cartId, Integer quantity) {
        Optional<Cart> deleteData = bookStoreCartRepository.findById(cartId);
        if (deleteData.isPresent()) {
            Optional<BookData> book = bookStoreRepository.findById(deleteData.get().getBook().getBookId());
            System.out.println("book.get().getQuantity() + quantity" + (book.get().getQuantity() + quantity));
            book.get().setQuantity(book.get().getQuantity() + quantity);
            bookStoreRepository.save(book.get());
            bookStoreCartRepository.deleteById(cartId);
            return deleteData;
        } else {
            return Optional.empty();// Did not get any cart for specific cart id
        }
    }

    @Override
    public Cart insertItems(CartDTO cartdto) {
        Optional<BookData> book = bookStoreRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findById(cartdto.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Cart newCart = new Cart(cartdto.getQuantity(), book.get(), userRegistration.get());
            if (cartdto.getQuantity() <= newCart.getQuantity() && cartdto.getQuantity()>0  ) {
                book.get().setQuantity((book.get().getQuantity()) - (cartdto.getQuantity()));
                bookStoreRepository.save(book.get());
                bookStoreCartRepository.save(newCart);
                return newCart;
            }
            return null;
        } else {
            return null;
        }
    }


}