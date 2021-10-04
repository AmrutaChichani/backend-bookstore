package com.springboot.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.bookstore.dto.CustomerDto;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Entity
@Table(name="customer")
@SQLDelete(sql="UPDATE customer SET active_flag=0 where customer_id=?")
@Where(clause="active_flag=1")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Integer id;

	@NotBlank
	@Pattern(regexp="^[a-zA-Z]$",message = "Enter valid name.")
	@Column(name="name")
	private String name;

	@Email
	@Column(name="email")
	private String email;

	@Pattern(regexp="^[0-9]{10}",message = "Enter valid contact no.")
	@Column(name="contact_no")
	private String contactNo;

	@OneToMany( cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private List<CustomerAddress> customerAddresses;

	@JsonIgnore
	@OneToMany()
	@JoinColumn(name="customer_id")
	private List<Order> orders;

	@Column(name="active_flag",columnDefinition = "TINYINT default 1", length = 1)
	private Integer activeFlag;

}
