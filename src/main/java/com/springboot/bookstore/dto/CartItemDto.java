package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.CartItems;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CartItemDto {
    private Integer id;

    @NotNull
    private Integer book;

    @Min(value=1)
    private Integer quantity;

    @NotNull
    private Integer cart;


    public static CartItemDto from(CartItems cartItems) {
        CartItemDto dto=new CartItemDto();
        if(cartItems.getId()!=null)
            dto.setId(cartItems.getId());
        dto.setQuantity(cartItems.getQuantity());
        dto.setBook(cartItems.getBook().getId());
        dto.setCart(cartItems.getCart().getCartId());
        return dto;
    }
}
