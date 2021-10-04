package com.springboot.bookstore.dao;

import com.springboot.bookstore.entity.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublishersDAO extends CrudRepository<Publisher, Integer>{

	
}
