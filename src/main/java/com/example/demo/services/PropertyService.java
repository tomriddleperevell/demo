package com.example.demo.services;


import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerAndContact;
import com.example.demo.model.Property;
import com.example.demo.repository.PropertyRepository;
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
public class PropertyService {
	private PropertyRepository propertyRepository;
	@PersistenceContext
	private EntityManager em;

	@Autowired
	public  PropertyService(PropertyRepository propertyRepository){
		this.propertyRepository = propertyRepository;


	}


	public Property get(long id) {
		Optional<Property> myProperty = propertyRepository.findById(id);

		if(myProperty.isPresent() ){
			return myProperty.get();

		}else{
		    throw new RuntimeException("don't have property with that id");



		}

	}



	public Property add(Property property) {
		return propertyRepository.save(property);
	}


	public List<Property> findByState(String state){
		String queryBuilder = "SELECT p FROM Property p WHERE 1 = 1";

		Map<String,Object> params = new HashMap<>();

		if(state != null && !state.equals("")){
			queryBuilder+=" and p.state like :state || '%' ";
			params.put("state",state);
		}

		Query query = em.createQuery(queryBuilder, Property.class);

		params.forEach((key, value) -> {
			query.setParameter(key, value);
		});

		return (List<Property>)query.getResultList();


		//return propertyRepository.findByState(state);

	}

	public List<Property> findByCustomerName(String name){
		return propertyRepository.findByCustomerName(name);

	}

	public List<CustomerAndContact> findContactByNameAndPropertyInState(String customerName, String state){
		return propertyRepository.findContactByNameAndPropertyInState(customerName,state);
	}




}
