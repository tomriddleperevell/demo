package com.example.demo.services;

import com.example.demo.model.Contact;
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

	public Loan add(Loan loan) {
		return loanRepository.save(loan);
	}

	public Loan update(long id, Loan loan) {
		Loan existingLoan = get(id);
		existingLoan.setLeftAmount(loan.getLeftAmount());
		existingLoan.setTotalAmount(loan.getTotalAmount());
		return loanRepository.save(existingLoan);
	}

	public List<Loan> getCustomerByName(String name) {
		return loanRepository.getCustomerByName(name);
	}

	public List<Object> getContactByTotalAmount(Integer total, Contact.Type type) {
		return loanRepository.getContactByTotalAmount(total,type);
	}
}
