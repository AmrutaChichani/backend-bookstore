package com.springboot.bookstore.dao;

import com.springboot.bookstore.entity.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrdersDAO extends CrudRepository<Order, Integer>{
    @Modifying
    @Query(value="UPDATE orders SET active_flag=false, status='CANCEL' WHERE order_id=?1", nativeQuery = true)
    public Integer cancelOrder(Integer orderId);
	
}
