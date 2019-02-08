package com.example.demo.services;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	public Customer get(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new RuntimeException("customer not found");
		}
		return customer.get();
	}

	public Customer add(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer update(Long id, Customer customer) {
		Customer existingCustomer = get(id);
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());
		existingCustomer.setAge(customer.getAge());
		return customerRepository.save(existingCustomer);
	}
	public Customer updateFirstName(Long id,String firstname) {
		Customer existingCustomer = get(id);
		existingCustomer.setFirstName(firstname);
		return customerRepository.save(existingCustomer);
	}
	/*public Customer updateLastName(Long id,String lastname){
		Customer existingCustomer = get(id);
		existingCustomer.setLastName(lastname);
		return customerRepository.save(existingCustomer);
	}
	public Customer updateAge(Long id,Integer age) {
		Customer existingCustomer = get(id);
		existingCustomer.setAge(age);
		return customerRepository.save(existingCustomer);
	}*/

	public Customer delete(long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		System.out.println("EEAA" + id);
		if (!customer.isPresent()) {
			throw new RuntimeException("customer not found");
		}
		System.out.println("AQAA" + id);
		customerRepository.deleteById(id);
		return customer.get();
	}
}