package com.springboot.bookstore.service;

import com.springboot.bookstore.dao.CustomerDAO;
import com.springboot.bookstore.dto.CustomerDto;
import com.springboot.bookstore.entity.Customer;
import com.springboot.bookstore.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServices {

        @Autowired
        private CustomerDAO customerDao;

        @Autowired
        private CustomerAddressServices addressServices;

        public List<Customer> findAll()
        {
            List<Customer> customers=new ArrayList<>();
            customerDao.findAll().forEach(customers::add);
            return customers;
        }
        public Customer findByCustomerId(Integer customerId)
        {
            Optional<Customer> customerOptional=customerDao.findById(customerId);
            if(!customerOptional.isPresent()){
                log.info("Invalid customer id:{}",customerId);
                throw new BadRequestException();
            }
            return customerOptional.get();

        }

        public Integer addCustomer(CustomerDto customer)
        {
            Customer theCustomer=new Customer();
            if(customer.getId()!=null)
                theCustomer.setId(customer.getId());
            theCustomer.setName(customer.getName());
            theCustomer.setActiveFlag(1);
            theCustomer.setEmail(customer.getEmail());
            theCustomer.setContactNo(customer.getContactNo());
            customerDao.save(theCustomer);
            return theCustomer.getId();
        }

        public void deleteCustomerById(Integer customerId)
        {
            if(customerDao.existsById(customerId)){
            customerDao.deleteById(customerId);
            }else{
                log.info("Invalid customer id :{}",customerId);
                throw new BadRequestException();
            }
        }
}
