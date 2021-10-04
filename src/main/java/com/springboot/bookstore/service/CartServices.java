package com.springboot.bookstore.service;

import com.springboot.bookstore.dao.CartsDAO;
import com.springboot.bookstore.dao.CustomerDAO;
import com.springboot.bookstore.entity.Cart;
import com.springboot.bookstore.entity.Customer;
import com.springboot.bookstore.exception.BadRequestException;
import com.springboot.bookstore.exception.InactiveCartException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CartServices {

	@Autowired
	private CartsDAO cartsDao;

	@Autowired
	private CustomerDAO customerDao;

	public Integer getCarts(Integer customerId) {

		Optional<Customer> customerOptional=customerDao.findById(customerId);
		if(customerOptional.isPresent()) {
			Integer activeCart = cartsDao.findByCustomerId(customerId);
			if (activeCart == null) {
				Cart newCart = new Cart();
				newCart.setCustomer(customerOptional.get());
				newCart.setActiveFlag(1);
				cartsDao.save(newCart);
				activeCart = cartsDao.findByCustomerId(customerId);
			}
			return(activeCart);
		}else{
			log.info("Invalid Customer Id :{}",customerId);
			 throw new BadRequestException();
		}
	}

	public Cart getCartsById(Integer cartId) {

		Optional<Cart> cartOptional = cartsDao.findById(cartId);
		if(!cartOptional.isPresent()) {
			log.info("Invalid Cart Id {}", cartId);
			throw new InactiveCartException(cartId);
		}
		return cartOptional.get();
	}

	public void deleteCartById(Integer cartId)
	{
		if(cartsDao.existsById(cartId)) {
			cartsDao.deleteById(cartId);
		}else{
			log.info("Invalid cart Id :{}",cartId);
			throw new BadRequestException();
		}
	}
	public void updateCart(Cart cart)
	{
		cartsDao.save(cart);
	}
}
