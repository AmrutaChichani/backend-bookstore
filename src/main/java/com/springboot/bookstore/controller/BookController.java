package com.springboot.bookstore.controller;

import com.springboot.bookstore.dto.BookDto;
import com.springboot.bookstore.dto.BookResponseDto;
import com.springboot.bookstore.service.CatalogService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class BookController {
	
	@Autowired
	CatalogService catalog;

	@GetMapping
	public ResponseEntity<List<BookResponseDto>> getCatalog() {

		return ResponseEntity.ok().body(catalog.getCatalog());
	}
	
	@GetMapping("/{bookId}")
	public ResponseEntity<BookResponseDto> getBook(@PathVariable ("bookId") Integer bookId) {
		return ResponseEntity.ok().body(catalog.getBook(bookId));
		
	}
	
	@DeleteMapping("/{bookId}")
	public ResponseEntity<Integer> deleteBook(@PathVariable("bookId") Integer bookId) {
		catalog.deleteBook(bookId);
		return ResponseEntity.ok().body(bookId);
	}
		
	@PostMapping
	public ResponseEntity<Integer> addOrUpdateBook(@Valid @RequestBody BookDto bookDto) {
		return ResponseEntity.ok().body(catalog.addOrUpdateBook(bookDto));
	}

}
