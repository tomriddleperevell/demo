package com.example.demo.controler;

import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.model.File;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("customers")
@PreAuthorize("hasAuthority('manager')")
public class CustomerController {
	private CustomerService customerService;
	private static String UPLOADED_FOLDER = "/home/tsotne/Desktop/destTest/";
	//@Autowired
	//private UserDetailsService userService;



	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public List<Customer> find(@RequestParam(required = false) String firstName,
							   @RequestParam(required = false) String lastName,
							   @RequestParam(required = false) Integer fromAge,
							   @RequestParam(required = false) Integer toAge) {
		return customerService.find(firstName, lastName, fromAge, toAge);
	}

	@GetMapping("/{id}")
	public Customer get(@PathVariable long id) {
		return customerService.get(id);
	}

	@PostMapping
	public Customer add(@RequestBody Customer customer) {
		return customerService.add(customer);
	}


	@PostMapping("/{customerId}/files") // //new annotation since 4.3
	public String singleFileUpload(@RequestParam MultipartFile file,
								   @PathVariable long customerId ) {

		System.out.println("here in controller");
		if (file.isEmpty()) {
			//redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			System.out.println("file's empty");
			return "redirect:uploadStatus";
		}

		try {

			System.out.println("reading file");

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());

			Files.write(path, bytes);
			//customerService.uploadFile(path.toString(),)
			//	redirectAttributes.addFlashAttribute("message",
			//			"You successfully uploaded '" + file.getOriginalFilename() + "'");

			customerService.uploadFile(customerId,file.getOriginalFilename(),UPLOADED_FOLDER + file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
			return "{\"success\":false}";
		}
		System.out.println("in the end");
		return "{\"success\":true}";
		//return "redirect:/uploadStatus";
	}

	@DeleteMapping("/{customerId}/files/{fileId}")
	public void deleteFileById(@PathVariable long fileId) {
		 customerService.deleteFileById(fileId);
	}


	@GetMapping("{id}/files")
	public List<File> getFiles(@PathVariable long id) {
		return customerService.getFiles(id);
	}

	@PutMapping("{id}")
	public Customer update(@PathVariable long id, @RequestBody Customer customer) {
		return customerService.update(id, customer);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable long id) {
		customerService.delete(id);
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

	@GetMapping("findLoan")
	public List<Customer> getByLoan(@RequestParam Integer totalAmount) {
		return customerService.getByLoan(totalAmount);
	}

	@GetMapping("{customerId}/contacts")
	public List<Contact> getAllContacts(@PathVariable long customerId) {
		return customerService.getAllContacts(customerId);
	}

	@GetMapping("{customerId}/findSpecificContact")
	public List<Contact> getSpecificContacts(@PathVariable long customerId, @RequestParam Contact.Type type) {
		return customerService.getSpecificContacts(customerId, type);
	}

	@GetMapping("{customerId}/findValueType")
	public Page<Object> getSpecificValue(@PathVariable long customerId, @RequestParam Contact.Type type, @RequestParam Integer page, @RequestParam Integer size) {
		return customerService.getSpecificValue(customerId, type, page, size);
	}

}
