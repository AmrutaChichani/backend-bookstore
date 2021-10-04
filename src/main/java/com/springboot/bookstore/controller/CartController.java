package com.springboot.bookstore.controller;

import com.springboot.bookstore.dto.CartDto;
import com.springboot.bookstore.service.CartServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartServices cartServices;

    @PostMapping("/{customerId}")
    public ResponseEntity<Integer> createOrGetCart(@PathVariable("customerId") Integer customerId) {
        Integer result= cartServices.getCarts(customerId);
        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartByID(@PathVariable("cartId") Integer cartId) {
        CartDto result=CartDto.from(cartServices.getCartsById(cartId));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Integer> deleteCartByID(@PathVariable("cartId") Integer cartID) {
        cartServices.deleteCartById(cartID);
        return ResponseEntity.ok().body(cartID);

    }
}
