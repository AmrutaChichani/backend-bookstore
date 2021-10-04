package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.CartItems;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CartItemResponseDto {
    private Integer id;

    @NotNull
    private Integer book;

    @Min(value=1)
    private Integer quantity;

    public static CartItemResponseDto from(CartItems cartItems) {
        CartItemResponseDto dto=new CartItemResponseDto();
        if(cartItems.getId()!=null)
            dto.setId(cartItems.getId());
        dto.setQuantity(cartItems.getQuantity());
        dto.setBook(cartItems.getBook().getId());
        return dto;
    }
}
