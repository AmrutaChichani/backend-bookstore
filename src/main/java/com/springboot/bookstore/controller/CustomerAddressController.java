package com.springboot.bookstore.controller;

import com.springboot.bookstore.dto.CustomerAddressDto;
import com.springboot.bookstore.entity.CustomerAddress;
import com.springboot.bookstore.service.CustomerAddressServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer/address")
public class CustomerAddressController {
    @Autowired
    private CustomerAddressServices customerAddressServices;

    @PostMapping
    public ResponseEntity<Integer> addOrUpdateAddress(@Valid @RequestBody CustomerAddressDto address) {

        Integer result= customerAddressServices.addOrUpdateAddress(address);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Integer> deleteAddressById(@PathVariable("addressId") Integer addressId) {
        customerAddressServices.deleteAddress(addressId);
        return ResponseEntity.ok().body(addressId);
    }

}
