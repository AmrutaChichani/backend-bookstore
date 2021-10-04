package com.springboot.bookstore.controller;

import com.springboot.bookstore.dto.CartItemDto;
import com.springboot.bookstore.entity.CartItems;
import com.springboot.bookstore.service.CartItemsServices;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cart")
public class CartItemController {

    @Autowired
    private CartItemsServices cartItemsServices;

    @PostMapping
    public ResponseEntity<Integer> addToCart(@Valid @RequestBody CartItemDto cartItem) {
       Integer result= cartItemsServices.addToCart(cartItem);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<Integer> deleteFromCart(@PathVariable("itemId") Integer cartItemId) {
        cartItemsServices.deleteFromCart(cartItemId);
        return ResponseEntity.ok().body(cartItemId);
    }

}
