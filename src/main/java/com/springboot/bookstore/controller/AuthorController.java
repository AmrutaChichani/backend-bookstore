package com.springboot.bookstore.controller;

import com.springboot.bookstore.dto.AuthorDto;
import com.springboot.bookstore.entity.Author;
import com.springboot.bookstore.service.AuthorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    @Autowired
    private AuthorServices authorServices;


    @GetMapping
    public ResponseEntity <List<AuthorDto>> getAuthors(){
        List<AuthorDto> authorsDtoList=new ArrayList<>();
        List<Author> authors=authorServices.getAuthors();
        for (Author author:authors) {
            AuthorDto theAuthor=AuthorDto.from(author);
            authorsDtoList.add(theAuthor);
        }
        return ResponseEntity.ok().body(authorsDtoList);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable("authorId") Integer authorID){
        Author author=authorServices.getAuthorById(authorID);
        AuthorDto authorDto= AuthorDto.from(author);

        return ResponseEntity.ok().body(authorDto);
    }

    @PostMapping
    public ResponseEntity<Integer> addOrUpdateAuthor(@Valid @RequestBody AuthorDto authorDto){
        Author author=new Author();
        author.setId(authorDto.getId());
        author.setName(authorDto.getName());
        author.setEmail(authorDto.getEmail());
          return ResponseEntity.ok().body(authorServices.addOrUpdateAuthor(author));
    }
}
