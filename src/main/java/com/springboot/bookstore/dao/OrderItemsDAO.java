package com.springboot.bookstore.dao;

import com.springboot.bookstore.entity.OrderItems;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemsDAO extends CrudRepository<OrderItems, Integer>{

	
}
