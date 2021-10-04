package com.springboot.bookstore.service;

import com.springboot.bookstore.dao.OrderItemsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsServices {
        @Autowired
        private OrderItemsDAO orderItemsDao;

}
