package com.springboot.bookstore.dao;

import com.springboot.bookstore.entity.CustomerAddress;
import org.springframework.data.repository.CrudRepository;

public interface CustomerAddressDAO extends CrudRepository<CustomerAddress, Integer>{

	
}
