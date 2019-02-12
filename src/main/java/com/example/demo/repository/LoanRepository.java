package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	@Query("SELECT l FROM Loan l " +
			"left join Customer c on c.id = l.customerId " +
			"where c.firstName like :name || '%'")
	List<Loan>  getCustomerByName(@Param("name") String name);
}
