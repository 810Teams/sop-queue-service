package com.bank.springhibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customer;
	private String name;
	
	
	private String review;
	
	public Comment() {
		super();
	}

	public Comment(Long id,String customer, String name, String comment) {
		super();
		this.id = id;
		this.customer = customer;
		this.name = name;
		this.review = comment;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String comment) {
		this.review = comment;
	}
		
}