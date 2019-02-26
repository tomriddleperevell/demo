package com.example.demo.repository;

import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.model.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//	List<Customer> findByAgeBetween(int ageFrom, int ageTo);

	@Query("SELECT c FROM Customer c " +
			"where c.age between :ageFrom and :ageTo " +
			"order by c.firstName DESC")
	List<Customer> findByAge(@Param("ageFrom") int ageFrom, @Param("ageTo") int ageTo);

	@Query("SELECT c FROM Customer c " +
			"WHERE c.firstName Like :name || '%' ")
	List<Customer> findByName(@Param ("name") String name);

	@Query("SELECT c FROM Customer c " +
			"join c.loans l " +
			"where l.totalAmount > :totalAmount")
	List<Customer> findByLoanTotalAmount(@Param("totalAmount") int totalAmount);


	@Query("SELECT co From Contact co " +
			"join Customer c on co.customerId = c.id " +
			"WHERE c.id = :customerId")
	List<Contact> getAllContacts(@Param ("customerId") long customerId);

	@Query("SELECT co From Contact co " +
			"join Customer c on co.customerId = c.id " +
			"WHERE c.id = :customerId and co.type = :type")
	List<Contact> getSpecificContacts(@Param("customerId") long customerId, @Param("type") Contact.Type type);

	@Query("SELECT co.value,c.firstName From Contact co " +
			"join Customer c on co.customerId = c.id " +
			"WHERE c.id = :customerId and co.type = :type " +
			"order by  co.value DESC")
	Page<Object> getSpecificValue(@Param ("customerId") long customerId, @Param("type") Contact.Type type, Pageable pageable);

	@Query("SELECT f FROM Customer c " +
			"JOIN c.files f " +
			"WHERE c.id = :customerId")
	List<File> getFiles(@Param ("customerId") long customerId);

	/*@Query(value = "SELECT * FROM customers c where c.age between :ageFrom and :ageTo", nativeQuery = true)
	List<Customer> findNative(@Param("ageFrom") int ageFrom, @Param("ageTo") int ageTo);*/
}