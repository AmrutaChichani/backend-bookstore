package com.springboot.bookstore.dao;

import com.springboot.bookstore.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<Customer, Integer>{

}
