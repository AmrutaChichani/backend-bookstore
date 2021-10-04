package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.Book;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BookResponseDto {

    private Integer id;
    private String title;
    private String category;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;
    private Double price;
    private List<AuthorDto> authors;
    private PublisherDto publisher;

    public static BookResponseDto from(Book book){
        BookResponseDto dto = new BookResponseDto();
        if(book.getId()!=null)
            dto.setId(book.getId());
        dto.setTitle(book.getName());
        dto.setPublishDate(book.getPublishDate());
        dto.setPrice(book.getPrice());
        dto.setCategory(book.getCategory());

        List<AuthorDto> authorsDtoList=new ArrayList<>();
        book.getAuthors().forEach(author->authorsDtoList.add(AuthorDto.from(author)));
        dto.setAuthors(authorsDtoList);

        dto.setPublisher(PublisherDto.from(book.getPublisher()));

        return dto;
    }

}
