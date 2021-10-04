package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.Book;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class BookDto {

    private Integer id;
    private String title;
    private String category;
    private Double price;
    private List<Integer> authorId;
    private Integer publisherId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    public static BookDto from(Book book){
        BookDto dto = new BookDto();
        if(book.getId()!=null)
            dto.setId(book.getId());
        dto.setTitle(book.getName());
        dto.setPublishDate(book.getPublishDate());
        dto.setPrice(book.getPrice());
        dto.setCategory(book.getCategory());
        dto.setPublisherId(book.getPublisher().getId());

        List<Integer> authorsList=new ArrayList<>();
        book.getAuthors().forEach(author->authorsList.add(author.getId()));
        dto.setAuthorId(authorsList);

        return dto;
    }

}
