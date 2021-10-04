package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.ShippingAddress;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ShippingAddressResponseDto {
    private Integer id;
    private String locality;
    private String city;
    private String state;
    private String pinCode;


    public static ShippingAddressResponseDto from(ShippingAddress address){
        ShippingAddressResponseDto dto=new ShippingAddressResponseDto();
        if(address.getShippingId()!=null)
             dto.setId(address.getShippingId());
        dto.setPinCode(address.getPinCode());
        dto.setState(address.getState());
        dto.setCity(address.getCity());
        dto.setLocality(address.getLocality());
        return dto;
    }

}
