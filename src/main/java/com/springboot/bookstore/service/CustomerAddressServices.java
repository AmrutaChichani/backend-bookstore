package com.springboot.bookstore.service;

import com.springboot.bookstore.dao.CustomerAddressDAO;
import com.springboot.bookstore.dto.CustomerAddressDto;
import com.springboot.bookstore.entity.Customer;
import com.springboot.bookstore.entity.CustomerAddress;
import com.springboot.bookstore.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerAddressServices {

        @Autowired
        private CustomerAddressDAO customerAddressDao;

        @Autowired
        private CustomerServices customerServices;

        public Integer addOrUpdateAddress(CustomerAddressDto address)
        {
            CustomerAddress theAddress=new CustomerAddress();
            if(address.getId()!=null)
                    theAddress.setId(address.getId());
            theAddress.setCity(address.getCity());
            theAddress.setLocality(address.getLocality());
            theAddress.setState(address.getState());
            theAddress.setCountry(address.getCountry());
            theAddress.setPinCode(address.getPinCode());
            theAddress.setCustomerId(customerServices.findByCustomerId(address.getCustomerId()));

            customerAddressDao.save(theAddress);
            return theAddress.getId();
        }

        public void deleteAddress(Integer addressId) {
            if (!customerAddressDao.existsById(addressId)) {
                log.info("Invalid Address id:{}", addressId);
                throw new BadRequestException();
            }
            customerAddressDao.deleteById(addressId);
        }
}
