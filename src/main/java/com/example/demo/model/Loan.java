package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "loans")
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "total_amount")
	private Integer totalAmount;

	@Column(name = "left_amount")
	private Integer leftAmount;

	@Column(name = "service_name")
	private String serviceName;

	@Column(name = "customer_id")
	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Long getId() {
		return id;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public Integer getLeftAmount() {
		return leftAmount;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setLeftAmount(Integer leftAmount) {
		this.leftAmount = leftAmount;
	}
}
