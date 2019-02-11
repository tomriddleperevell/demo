package com.example.demo.repository;

import com.example.demo.model.Customer;
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

	/*@Query(value = "SELECT * FROM customers c where c.age between :ageFrom and :ageTo", nativeQuery = true)
	List<Customer> findNative(@Param("ageFrom") int ageFrom, @Param("ageTo") int ageTo);*/
}