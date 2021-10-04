package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.Author;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AuthorDto {

    private Integer id;

    @NotBlank
    private String name;

    @Email
    private String email;


    public static AuthorDto from(Author author){
        AuthorDto dto = new AuthorDto();
        dto.setName(author.getName());
        dto.setEmail(author.getEmail());
        if(author.getId()!=null)
            dto.setId(author.getId());
        return dto;
    }
}
