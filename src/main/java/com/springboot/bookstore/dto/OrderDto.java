package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.Order;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Integer id;
    private Double totalAmount;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    private List<OrderItemDto> orderItems;
    private ShippingAddressResponseDto shippingAddress;

    public static OrderDto from(Order order){
        OrderDto dto=new OrderDto();
        if(order.getOrderId()!=0)
            dto.setId(order.getOrderId());

        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderDate(order.getOrderDate());

        List<OrderItemDto> orderItemsList=new ArrayList<>();
        order.getOrderItems().forEach(item->orderItemsList.add(OrderItemDto.from(item)));
        dto.setOrderItems(orderItemsList);
        return dto;
    }
}
