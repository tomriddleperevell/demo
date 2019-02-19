package com.example.demo.repository;

import com.example.demo.model.Contact;
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

	@Query("SELECT DISTINCT c,cu.firstName FROM Loan l " +
			"join Contact c on c.customerId = l.customerId " +
			"join Customer cu on cu.id = c.customerId " +
			"WHERE l.totalAmount > :total and c.type = :type")
	List<Object> getContactByTotalAmount(@Param ("total")Integer total, @Param("type") Contact.Type type);
}
