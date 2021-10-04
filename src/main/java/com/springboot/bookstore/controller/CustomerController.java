package com.springboot.bookstore.controller;

import com.springboot.bookstore.dto.CustomerDto;
import com.springboot.bookstore.dto.CustomerResponseDto;
import com.springboot.bookstore.entity.Customer;
import com.springboot.bookstore.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getCustomers() {
        List<Customer> customerList=customerServices.findAll();
        List<CustomerResponseDto> customerDtoList=new ArrayList<>();
        for (Customer customer:customerList){
            customerDtoList.add(CustomerResponseDto.from(customer));
        }
        return ResponseEntity.ok().body(customerDtoList);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable("customerId") Integer customerId) {
        CustomerResponseDto result=CustomerResponseDto.from(customerServices.findByCustomerId(customerId));
         return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<Integer> addOrUpdateCustomer(@Valid @RequestBody CustomerDto customer) {

        Integer result=customerServices.addCustomer(customer);
            return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Integer> deleteCustomerById(@PathVariable("customerId") Integer customerId) {
        customerServices.deleteCustomerById(customerId);
        return ResponseEntity.ok().body(customerId);
    }
}
