package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager em;

	public List<User> find(String userName){
		String queryBuilder = "SELECT u FROM User u WHERE 1 = 1";
		Map<String,Object> params = new HashMap<>();
		if(userName != null && !userName.isEmpty()) {
			queryBuilder += " and u.userName = :userName";
			params.put("userName",userName);
		}
		Query query = em.createQuery(queryBuilder, User.class);

		params.forEach((key, value) -> {
			query.setParameter(key, value);
		});

		return (List<User>)query.getResultList();
	}

	User getUserById(Long id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()){
			throw new RuntimeException();
		}

		return user.get();
	}




}
