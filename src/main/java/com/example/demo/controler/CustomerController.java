package com.example.demo.controler;

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

	@GetMapping("{id}")
	public Customer get(@PathVariable long id) {
		return customerService.get(id);
	}

	@PostMapping
	public Customer add(Customer customer) {
		return customerService.add(customer);
	}

	@PutMapping("{id}")
	public Customer update(@PathVariable long id, Customer customer) {
		return customerService.update(id, customer);
	}

	@PutMapping("{id}/{firstName)")
	public Customer updateFirstName(@PathVariable long id,String firstName) {
		return customerService.updateFirstName(id, firstName);
	}

	@DeleteMapping("/delete/{id}")
	public Customer delete(@PathVariable long id) {
		System.out.println("AAAAAA");
		return customerService.delete(id);
	}
}
