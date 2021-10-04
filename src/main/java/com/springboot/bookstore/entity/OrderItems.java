package com.springboot.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="order_items")
public class OrderItems {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;


		@JsonIgnore
		@ManyToOne
		@JoinColumn(name="order_id")
		private Order order;


		@ManyToOne
		@JoinColumn(name="book_id")
		private Book book;
		
		@Column(name="quantity")
		private int quantity;


}
