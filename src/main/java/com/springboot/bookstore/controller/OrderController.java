package com.springboot.bookstore.controller;

import com.springboot.bookstore.dto.OrderDto;
import com.springboot.bookstore.dto.ShippingAddressDto;
import com.springboot.bookstore.entity.ShippingAddress;
import com.springboot.bookstore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/{cartId}")
    public ResponseEntity<Integer> placeOrder(@PathVariable("cartId") Integer cartId) {
        log.debug("Inside OrderController");
        log.info("Placing order for cart id: {}",cartId);
        Integer result=orderService.placeOrder(cartId);
        return ResponseEntity.ok().body(result);
    }
    @PostMapping("/address")
    public ResponseEntity<Integer> mapShippingAddress(@Valid @RequestBody ShippingAddressDto shippingAddress) {

        Integer result=orderService.addOrUpdateShippingAddress(shippingAddress);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> viewOrder(@PathVariable("orderId") Integer orderId) {
        OrderDto result= orderService.viewOrderById(orderId);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Integer> cancelOrder(@PathVariable("orderId") Integer orderId) {
        Integer result=orderService.cancelOrder(orderId);
        return ResponseEntity.ok().body(result);
    }
}
