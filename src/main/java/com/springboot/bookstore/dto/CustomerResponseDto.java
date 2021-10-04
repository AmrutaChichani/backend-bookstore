package com.springboot.bookstore.dto;

import com.springboot.bookstore.entity.Customer;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerResponseDto {
    private Integer id;
    private String name;
    private String email;
    private String contactNo;
    private List<CustomerAddressResponseDto> addresses;

    public static CustomerResponseDto from(Customer customer){
        CustomerResponseDto dto=new CustomerResponseDto();
        if(customer.getId()!=null)
            dto.setId(customer.getId());

        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setContactNo(customer.getContactNo());
        if(customer.getCustomerAddresses()!=null) {
            List<CustomerAddressResponseDto> addressDtoList = new ArrayList<>();
            customer.getCustomerAddresses().forEach(address ->
                    addressDtoList.add(CustomerAddressResponseDto.from(address)));
            dto.setAddresses(addressDtoList);
        }
        return dto;
    }
}
