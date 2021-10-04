package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.Cart;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDto {
    private Integer id;
    private Integer customerId;
    private List<CartItemResponseDto> cartItems;

    public static CartDto from(Cart cart){
        CartDto dto=new CartDto();
        if(cart.getCartId()!=null)
            dto.setId(cart.getCartId());
        if(cart.getCustomer()!=null){
            dto.setCustomerId(cart.getCustomer().getId());
        }
        if(cart.getCartItems()!=null) {
            List<CartItemResponseDto> cartItemDtoList = new ArrayList<>();
            cart.getCartItems().forEach(cartItem -> cartItemDtoList.add(CartItemResponseDto.from(cartItem)));
            dto.setCartItems(cartItemDtoList);
        }

        return dto;
    }
}
