package com.springboot.bookstore.dao;

import com.springboot.bookstore.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorsDAO extends CrudRepository<Author, Integer>{

	
}
