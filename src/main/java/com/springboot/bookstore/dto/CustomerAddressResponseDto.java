package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.CustomerAddress;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class CustomerAddressResponseDto {
        private Integer id;
        private String locality;
        private String city;
        private String state;
        private String country;

        @Pattern(regexp = "^[0-9]{6}",message = "Enter 6 digit Pin code")
        private String pinCode;


        public static CustomerAddressResponseDto from(CustomerAddress address){
            CustomerAddressResponseDto dto=new CustomerAddressResponseDto();
            dto.setId(address.getId());
            dto.setPinCode(address.getPinCode());
            dto.setCountry(address.getCountry());
            dto.setState(address.getState());
            dto.setCity(address.getCity());
            dto.setLocality(address.getLocality());
            return dto;
        }
}
