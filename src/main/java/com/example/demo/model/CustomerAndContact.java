package com.example.demo.model;

public class CustomerAndContact {
	private String customerName;
	private Contact.Type contactType;
	private String contactValue;


	public CustomerAndContact(String customerName, Contact.Type contactType, String contactValue) {
		this.customerName = customerName;
		this.contactType = contactType;
		this.contactValue = contactValue;
	}

	public CustomerAndContact() {
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Contact.Type getContactType() {
		return contactType;
	}

	public void setContactType(Contact.Type contactType) {
		this.contactType = contactType;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}
}