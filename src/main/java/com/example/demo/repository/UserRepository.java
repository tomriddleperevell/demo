package com.example.demo.repository;

import com.example.demo.model.Authority;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String username);

	@Query("SELECT  a FROM User u " +
			"join UserAuthority  ua on u.id = ua.userId " +
			"join Authority a on ua.authorityId = a.id " +
			"WHERE u.id = :userId ")
	List<Authority> getAuthorities(@Param("userId") long userId);


}
