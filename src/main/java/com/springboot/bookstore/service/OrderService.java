package com.springboot.bookstore.service;

import com.springboot.bookstore.dao.OrderItemsDAO;
import com.springboot.bookstore.dao.OrdersDAO;
import com.springboot.bookstore.dto.OrderDto;
import com.springboot.bookstore.dto.ShippingAddressDto;
import com.springboot.bookstore.dto.ShippingAddressResponseDto;
import com.springboot.bookstore.entity.*;
import com.springboot.bookstore.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class OrderService {
    @Autowired
    private OrdersDAO ordersDao;

    @Autowired
    private CartServices cartServices;

    @Autowired
    private OrderItemsDAO orderItemsDao;

    @Autowired
    private CartItemsServices cartItemsServices;

    @Autowired
    private ShippingAddressServices shippingAddressServices;

    private static final String INVALID_ORDER_ID="Invalid order id";

    public Integer placeOrder(Integer cartId) {

        Cart cart=cartServices.getCartsById(cartId);
        Order order= getOrderFromCart(cart);
        ordersDao.save(order);
        cart.setActiveFlag(0);
        cartServices.updateCart(cart);
        return order.getOrderId();

    }
        private List<OrderItems> getItemsFromCart(List<CartItems> cartItems){

            List<OrderItems> orderItems=new ArrayList<>();
            if(cartItems!=null){
            for(CartItems item:cartItems) {
                OrderItems orderItem=new OrderItems();
                orderItem.setBook(item.getBook());
                orderItem.setQuantity(item.getQuantity());
                orderItems.add(orderItem);
            }
            return orderItems;
            }
            else {
                log.info("No items found in cart");
                throw new BadRequestException();
            }
        }

        private Order getOrderFromCart(Cart cart)
        {
            Order order=new Order();

            Date date = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            List<CartItems> cartItems= cart.getCartItems();
            List<OrderItems> orderItems=getItemsFromCart(cartItems);

            order.setActiveFlag(1);
            order.setCustomerId(cart.getCustomer());
            order.setOrderDate(date);
            order.setStatus("CONFIRM");
            order.setOrderItems(orderItems);
            order.setTotalAmount(calculateAmount(cartItems));
            return order;
        }

    private double calculateAmount(List<CartItems> cartItems) {
        double result=0.0;
        for(CartItems item:cartItems){
            result+=item.getQuantity()*item.getBook().getPrice();
        }
        return result;
    }
    public OrderDto viewOrderById(Integer orderId)
    {
        Optional<Order> orderOptional=ordersDao.findById(orderId);
        if(!orderOptional.isPresent())
        {
            log.info("{}:{}",INVALID_ORDER_ID,orderId);
            throw new BadRequestException();
        }
        OrderDto dto= OrderDto.from(orderOptional.get());
        ShippingAddress address=shippingAddressServices.findByOrderId(orderId);
        if(address!=null) {
            ShippingAddressResponseDto responseDto = ShippingAddressResponseDto.from(address);
            dto.setShippingAddress(responseDto);
        }else {
            dto.setShippingAddress(null);
        }
        return dto;
    }
    public Integer cancelOrder(Integer orderId)
    {
        Integer result=0;
        if(ordersDao.existsById(orderId))
        {
           result= ordersDao.cancelOrder(orderId);
        }else{
           log.info("{} :{}",INVALID_ORDER_ID,orderId);
            throw new BadRequestException();
        }
        if(result==0){
            log.info("Unable to cancel order {}",orderId);
            throw new BadRequestException();

        }
        return orderId;
    }

    public Integer addOrUpdateShippingAddress(ShippingAddressDto addressDto){

        ShippingAddress address=new ShippingAddress();
        if(addressDto.getId()!=null)
            address.setShippingId(addressDto.getId());
        address.setCity(addressDto.getCity());
        address.setLocality(addressDto.getLocality());
        address.setState(addressDto.getState());
        address.setPinCode(addressDto.getPinCode());
        Optional<Order> orderOptional=ordersDao.findById(addressDto.getOrderID());
        if(!orderOptional.isPresent())
        {
            log.info("{} :{}",INVALID_ORDER_ID,addressDto.getOrderID());
            throw new BadRequestException();
        }
        address.setOrderId(orderOptional.get());
        return shippingAddressServices.addOrUpdateShippingAddress(address);
    }
}
