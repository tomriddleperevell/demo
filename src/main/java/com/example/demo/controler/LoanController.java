package com.example.demo.controler;

import com.example.demo.model.Contact;
import com.example.demo.model.Loan;
import com.example.demo.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("loans")
public class LoanController {
	private LoanService loanService;

	@Autowired
	public LoanController(LoanService loanService) { this.loanService = loanService; }

	@GetMapping
	public List<Loan> getAll() {
		return loanService.getAll();
	}


	@GetMapping("/{id}")
	public Loan get(@PathVariable long id) {
		return loanService.get(id);
	}

	@PostMapping
	public Loan add(@RequestBody Loan loan) {
		return loanService.add(loan);
	}

	@PutMapping("{id}")
	public Loan update(@PathVariable long id, @RequestBody Loan loan) {
		return loanService.update(id, loan);
	}

	@GetMapping("/prefix")
	public List<Loan> getCustomerByName(@RequestParam String name) {

		return loanService.getCustomerByName(name);
	}

	@GetMapping("contact")
	public List<Object> getContactByTotalAmount( @RequestParam Integer total, @RequestParam Contact.Type type) {
		return loanService.getContactByTotalAmount(total,type);
	}
}
