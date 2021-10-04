package com.springboot.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name="customer_address")
public class CustomerAddress {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customerId;
	
	@Column(name="locality")
	private String locality;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;

	@Pattern(regexp = "^[0-9]{6}",message = "Enter 6 digit Pin code")
	@Column(name="pincode")
	private String pinCode;

}
