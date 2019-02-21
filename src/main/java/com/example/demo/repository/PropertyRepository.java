package com.example.demo.repository;


import com.example.demo.model.CustomerAndContact;
import com.example.demo.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {


	@Query("SELECT  P FROM Property P "+
			" WHERE P.state LIKE :state || '%'")
	List<Property> findByState(@Param("state") String state);


	@Query("SELECT  p FROM Customer  c " +
			" JOIN  c.propertyList  p   " +
			" WHERE  c.firstName LIKE :name || '%'")
	List<Property> findByCustomerName(@Param("name") String name);


	@Query("SELECT new com.example.demo.model.CustomerAndContact(c.firstName, con.type, con.value) " +
			" FROM Customer c" +
			" JOIN c.propertyList p" +
			" JOIN c.contacts  con" +
			" WHERE c.firstName LIKE :customerName || '%'  AND p.state =:stateName ")
	List<CustomerAndContact> findContactByNameAndPropertyInState(@Param("customerName") String customerName,
																 @Param("stateName") String stateName);


}
