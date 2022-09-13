package com.bridgelabz.bookstorebackend.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderDTO {

    private Integer quantity;
    @NotEmpty(message = "Please provide address")
    private String address;

    private Integer price;
    private Integer userId;
    private Integer bookId;
    private boolean cancel;

    public OrderDTO() {
        super();
    }

    public OrderDTO(Integer quantity, @NotEmpty(message = "Please provide address") String address, Integer userId,
                    Integer bookId, boolean cancel, Integer price) {
        super();
        this.quantity = quantity;
        this.address = address;
        this.userId = userId;
        this.bookId = bookId;
        this.cancel = cancel;
    }
}