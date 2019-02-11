package com.example.demo.services;

import com.example.demo.model.Loan;
import com.example.demo.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
	private LoanRepository loanRepository;

	@Autowired
	public LoanService(LoanRepository loanRepository) {	this.loanRepository = loanRepository; }

	public List<Loan> getAll() {

		return loanRepository.findAll();
	}
	public Loan get(Long id) {
		Optional<Loan> loan = loanRepository.findById(id);
		if (!loan.isPresent()) {
			throw new RuntimeException("customer not found");
		}
		return loan.get();
	}
}
