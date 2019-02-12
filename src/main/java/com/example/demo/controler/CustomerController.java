package com.example.demo.controler;

import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public List<Customer> getAll() {
		return customerService.getAll();
	}

	@GetMapping("/search")
	public List<Customer> getAllByAge(@RequestParam Integer fromAge, @RequestParam Integer toAge) {
		return customerService.getAllByAge(fromAge, toAge);
	}

	@GetMapping("/{id}")
	public Customer get(@PathVariable long id) {
		return customerService.get(id);
	}

	@PostMapping
	public Customer add(@RequestBody Customer customer) {
		return customerService.add(customer);
	}

	@PutMapping("{id}")
	public Customer update(@PathVariable long id, @RequestBody Customer customer) {
		return customerService.update(id, customer);
	}

	@PutMapping("{id}/fn/{firstName}")
	public Customer updateFirstName(@PathVariable long id, @PathVariable String firstName) {
		return customerService.updateFirstName(id, firstName);
	}

	@PutMapping("{id}/ln/{lastname}")
	public Customer updateLastName(@PathVariable long id, @PathVariable String lastname) {
		return customerService.updateLastName(id, lastname);
	}

	@PutMapping("{id}/{age}")
	public Customer updateAge(@PathVariable long id, @PathVariable Integer age) {
		return customerService.updateAge(id, age);
	}

	@PutMapping("increment/{age}")
	public List<Customer> updateAllAges(@PathVariable Integer age) {
		return customerService.updateAllAge(age);
	}

	@DeleteMapping("{id}")
	public Customer delete(@PathVariable long id) {
		return customerService.delete(id);
	}

	@GetMapping("find")
	public List<Customer> getPrefix(@RequestParam String name) {
		return customerService.find(name);
	}

	@GetMapping("findLoan")
	public List<Customer> getByLoan(@RequestParam Integer totalAmount) {
		return customerService.getByLoan(totalAmount);
	}

	@GetMapping("{customerId}/contacts")
	public List<Contact> getAllContacts(@PathVariable long customerId) {
		return customerService.getAllContacts(customerId);
	}

	@GetMapping("findSpecificContact")
	public List<Contact> getSpecificContacts(@RequestParam long id, @RequestParam Contact.Type type) {
		return customerService.getSpecificContacts(id, type);
	}
}
