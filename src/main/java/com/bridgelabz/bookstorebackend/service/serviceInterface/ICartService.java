package com.bridgelabz.bookstorebackend.service.serviceInterface;

import com.bridgelabz.bookstorebackend.dto.CartDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.model.Cart;

import java.util.Optional;

public interface ICartService {

    Cart insertItems(CartDTO cartdto);

    ResponseDTO getCartDetails();


    Optional<Cart> getCartDetailsById(Integer cartId);

    Optional<Cart> deleteCartItemById(Integer cartId);

    Cart updateRecordById(Integer cartId, CartDTO cartDTO);

    Cart updateQuantity(Integer id, Integer quantity);

    Optional<Cart> deleteCartItemByIdAndQuantity (Integer cartId, Integer quantity);

}
