package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.Publisher;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class PublisherDto {
    private Integer id;

    @NotBlank
    private String name;

    @Pattern(regexp = "^[0-9]{10}$",message = "only numeric value of 10 digits")
    private String contactNo;

    public static PublisherDto from(Publisher publisher){
        PublisherDto dto = new PublisherDto();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        dto.setContactNo(publisher.getContactNo());
        return dto;
    }
}
