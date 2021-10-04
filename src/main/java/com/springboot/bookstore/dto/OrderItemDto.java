package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.OrderItems;
import lombok.Data;

@Data
public class OrderItemDto {
    private Integer id;
    private Integer bookId;
    private Integer quantity;


    public static OrderItemDto from(OrderItems orderItems){
        OrderItemDto dto=new OrderItemDto();
        if(orderItems.getId()!=null)
            dto.setId(orderItems.getId());
        dto.setBookId(orderItems.getBook().getId());
        dto.setQuantity(orderItems.getQuantity());

        return dto;
    }
}
