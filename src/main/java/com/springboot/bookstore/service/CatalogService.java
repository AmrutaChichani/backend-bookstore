package com.springboot.bookstore.service;

import com.springboot.bookstore.dao.BooksDAO;
import com.springboot.bookstore.dto.BookDto;
import com.springboot.bookstore.dto.BookResponseDto;
import com.springboot.bookstore.entity.Book;
import com.springboot.bookstore.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CatalogService {
	
	@Autowired
	 private BooksDAO booksDao;

	@Autowired
	private AuthorServices authorServices;

	@Autowired
	private PublisherServices publisherServices;

	public List<BookResponseDto> getCatalog() {
		List<BookResponseDto> result=new ArrayList<>();
		booksDao.findAll().forEach(book ->
			result.add(BookResponseDto.from(book)));
		return result;
	}
	
	public BookResponseDto getBook(int bookId) {

		Optional<Book> bookOptional=booksDao.findById(bookId);
		if(!bookOptional.isPresent())
		{
			log.info("Invalid book id:{}",bookId);
			throw new BadRequestException();
		}
		return BookResponseDto.from(bookOptional.get());
	}
	
	public Integer addOrUpdateBook(BookDto bookDto) {
		Book book=new Book();
		book.setId(bookDto.getId());
		book.setActiveFlag(1);
		book.setCategory(bookDto.getCategory());
		book.setPublishDate(bookDto.getPublishDate());
		book.setPrice(bookDto.getPrice());
		book.setName(bookDto.getTitle());
		book.setAuthors(authorServices.getAllAuthorsById(bookDto.getAuthorId()));
		book.setPublisher(publisherServices.getPublisherById(bookDto.getPublisherId()));

		booksDao.save(book);
		return book.getId();
	}

	public void deleteBook(int bookId) {
		if(booksDao.existsById(bookId)) {
			booksDao.deleteById(bookId);
		}else{
			log.info("Invalid book id:{}",bookId);
			throw new BadRequestException();
		}
	}

}

