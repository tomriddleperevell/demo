package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
	public enum Type {BLANK, MOBILE, HOME_PHONE, FAX}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "type_id")
	@Enumerated(EnumType.ORDINAL)
	private Type type;

	@Column(name = "value")
	private String value;

	@Column(name = "customer_id")
	private Long customerId;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}
