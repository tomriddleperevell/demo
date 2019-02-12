package com.example.demo.controler;

import com.example.demo.model.Contact;
import com.example.demo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
	private ContactService contactService;
	@Autowired()
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}
	@GetMapping
	public List<Contact> getAll() {
		return contactService.getAll();
	}
	@GetMapping("/{id}")
	public  Contact get( @PathVariable long id ) {
		return contactService.get(id);
	}


}
