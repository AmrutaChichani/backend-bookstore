package com.springboot.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="cart_items")
public class CartItems {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	@ManyToOne()
	private Book book;
	
	@Column(name="quantity")
	private int quantity;


}
