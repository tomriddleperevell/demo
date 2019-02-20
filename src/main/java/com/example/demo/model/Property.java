package com.example.demo.model;


import javax.persistence.*;

@Entity
@Table(name = "properties")
public class Property  {

	public enum Type {RESIDENTIAL,COMMERCIAL,LAND,INDUSTRIAL};






	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "customer_id")
	private long customerId;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;

	@Column(name = "address")
	private String address;

	@Column(name = "price")
	private int price;

	@Column(name = "type_id")
	@Enumerated(EnumType.ORDINAL)
	private Property.Type type;


	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
