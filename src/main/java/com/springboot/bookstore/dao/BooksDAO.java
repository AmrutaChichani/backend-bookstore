package com.springboot.bookstore.dao;

import com.springboot.bookstore.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BooksDAO extends CrudRepository<Book, Integer>{

	
}
