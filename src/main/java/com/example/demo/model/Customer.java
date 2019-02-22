package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;


	@Column(name = "personal_no")
	private String personalNo;

	@Column(name = "age")
	private Integer age;

	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Loan> loans;

	@OneToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Contact> contacts;



	@OneToMany
	@JoinColumn(name= "customer_id",referencedColumnName = "id")
	private List<Property> propertyList;

	@OneToMany
	@JoinColumn(name = "customer_id",referencedColumnName = "id")
	private List<File> files;

	public List<Contact> getContacts() {
		return contacts;
	}

	public String getPersonalNo() {
		return personalNo;
	}

	public void setPersonalNo(String personalNo) {
		this.personalNo = personalNo;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
