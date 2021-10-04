package com.springboot.bookstore.dao;

import com.springboot.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartsDAO extends CrudRepository<Cart, Integer> {

    @Query(value="SELECT c.cart_id as cart_id FROM cart c, customer cs WHERE  c.customer_id=cs.customer_id and c.active_flag=1 and c.customer_id=?1",
    nativeQuery=true)
    public Integer findByCustomerId(Integer customerId);




}
