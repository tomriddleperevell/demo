package com.example.demo.controler;



import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerAndContact;
import com.example.demo.model.Property;
import com.example.demo.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("properties")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;


	@GetMapping("{id}")
	public Property get(@PathVariable Long id){
		return propertyService.get(id);

	}

	@PostMapping
	public Property add(@RequestBody Property property ){

		return propertyService.add(property);
	}



	@GetMapping
	public List<Property> findByState(@RequestParam(required = false) String state){
		return propertyService.findByState(state);

	}


	@GetMapping("name")
	public List<Property> findByCustomerName(@RequestParam(required = false) String name){
		return propertyService.findByCustomerName(name);

	}
	@GetMapping("contact")
	public List<CustomerAndContact> findContactByNameAndPropertyInState(@RequestParam(required = false) String customerName,
																		@RequestParam(required = false) String state ){
		return propertyService.findContactByNameAndPropertyInState(customerName,state);

	}






}
