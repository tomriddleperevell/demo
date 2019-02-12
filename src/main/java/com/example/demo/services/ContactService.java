package com.example.demo.services;

import com.example.demo.model.Contact;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
	private ContactRepository contactRepository;
	@Autowired
	public ContactService(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	public List<Contact> getAll() {
		return contactRepository.findAll();
	}

	public Contact get(long id) {
		Optional<Contact> contact = contactRepository.findById(id);
		if (!contact.isPresent()) {
			throw new RuntimeException("customer not found");
		}
		return contact.get();
	}


}
