package com.springboot.bookstore.service;

import com.springboot.bookstore.dao.BooksDAO;
import com.springboot.bookstore.dao.CartItemsDAO;
import com.springboot.bookstore.dao.CartsDAO;
import com.springboot.bookstore.dao.CustomerDAO;
import com.springboot.bookstore.dto.CartItemDto;
import com.springboot.bookstore.entity.Book;
import com.springboot.bookstore.entity.Cart;
import com.springboot.bookstore.entity.CartItems;
import com.springboot.bookstore.exception.BadRequestException;
import com.springboot.bookstore.exception.InactiveCartException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartItemsServices {

    @Autowired
    private CartItemsDAO cartItemsDao;

    @Autowired
    private CustomerDAO customerDao;

    @Autowired
    private CartsDAO cartsDAO;

    @Autowired
    private BooksDAO booksDAO;

    public Integer addToCart(CartItemDto cartItemsDto)
    {
        CartItems cartItems=new CartItems();
        cartItems.setId(cartItemsDto.getId());
        cartItems.setQuantity(cartItemsDto.getQuantity());
        Optional<Book> book=booksDAO.findById(cartItemsDto.getBook());
        if(book.isPresent()) {
            cartItems.setBook(book.get());
        }else{
            log.info("Invalid book id :{}",cartItemsDto.getBook());
            throw new BadRequestException();
        }

        Optional<Cart> cartOptional= cartsDAO.findById(cartItemsDto.getCart());
        if(cartOptional.isPresent()) {
            cartItems.setCart(cartOptional.get());
            cartItemsDao.save(cartItems);
            return cartItems.getId();
        }else
        {
            Integer cartId=cartItems.getCart().getCartId();
            log.info("Inactive cart Id :{}",cartId);
            throw new InactiveCartException(cartId);
        }

    }

    public void deleteFromCart(Integer cartItemId) {
        if (!cartItemsDao.existsById(cartItemId)) {
            log.info("Invalid cart item id :{}",cartItemId);
            throw new BadRequestException();
        }
        cartItemsDao.deleteById(cartItemId);
    }
    public List<CartItems> getCartItemsByCartId(Integer cartId)
    {
        if(cartItemsDao.existsById(cartId))
        {
         return cartItemsDao.findAllByCartId(cartId);
        }else
        { log.info("Invalid cart id:{}",cartId);
            throw new BadRequestException();
        }
    }
}
