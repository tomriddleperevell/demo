package com.example.demo.controler;

import com.example.demo.model.Loan;
import com.example.demo.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
