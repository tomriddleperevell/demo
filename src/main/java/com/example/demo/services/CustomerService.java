package com.example.demo.services;

import com.example.demo.model.Contact;
import com.example.demo.model.Customer;
import com.example.demo.model.File;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {
	private FileRepository fileRepository;
	private CustomerRepository customerRepository;
	@PersistenceContext
	private EntityManager em;

	@Autowired
	public CustomerService(CustomerRepository customerRepository,FileRepository fileRepository) {
		this.customerRepository = customerRepository;
		this.fileRepository = fileRepository;
	}

	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	public Customer get(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (!customer.isPresent()) {
			throw new RuntimeException("customer not found");
		}
		return customer.get();
	}

	public File getFile(Long id){
		Optional<File> file = fileRepository.findById(id);
		if (!file.isPresent()) {
			throw new RuntimeException("customer not found");
		}
		return file.get();

	}

	public Customer add(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer update(Long id, Customer customer) {
		Customer existingCustomer = get(id);
		existingCustomer.setPersonalNo(customer.getPersonalNo());
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());
		existingCustomer.setAge(customer.getAge());
		return customerRepository.save(existingCustomer);
	}

	public Customer updateFirstName(Long id, String firstname) {
		Customer existingCustomer = get(id);
		existingCustomer.setFirstName(firstname);
		return customerRepository.save(existingCustomer);
	}



	@Transactional(rollbackFor = Throwable.class)
	public Customer updateLastName(Long id, String lastname) {
		Customer existingCustomer = get(id);
		existingCustomer.setLastName(lastname);
		return customerRepository.save(existingCustomer);
	}

	@Transactional(rollbackFor = Throwable.class)
	public Customer updateAge(Long id, Integer age) {
		Customer existingCustomer = get(id);
		existingCustomer.setAge(age);
		return customerRepository.save(existingCustomer);
	}

	@Transactional(rollbackFor = Throwable.class)
	public void delete(long id) {
		Customer customer = get(id);
		customerRepository.delete(customer);
	}

	@Transactional(rollbackFor = Throwable.class)
	public void deleteFileById(long id) {
		File file = getFile(id);
		fileRepository.delete(file);
	}



	public List<Customer> updateAllAge(Integer age) {
		List<Customer> allCustomer = customerRepository.findAll();
		for (Customer customer : allCustomer) {
			if (customer.getAge() != null) {
				customer.setAge(customer.getAge() + age);
			} else customer.setAge(77);
			customerRepository.save(customer);
		}
		return customerRepository.findAll();
	}

	public List<Customer> getAllByAge(Integer fromAge, Integer toAge) {
		return customerRepository.findByAge(fromAge, toAge);
	}

	public List<Customer> find(String name, String last, Integer ageFrom, Integer ageTo) {
		String queryBuilder = "SELECT c FROM Customer c where 1 = 1";
		Map<String, Object> params = new HashMap<>();
		if (name != null && !name.isEmpty()) {
			queryBuilder += " and c.firstName like :name||'%'";
			params.put("name", name);
		}
		if (last != null && !last.isEmpty()) {
			queryBuilder += " and c.lastName like :last||'%'";
			params.put("last", last);
		}
		if (ageFrom != null) {
			queryBuilder += " and c.age >= :ageFrom";
			params.put("ageFrom", ageFrom);
		}
		if (ageTo != null) {
			queryBuilder += " and c.age <= :ageTo";
			params.put("ageTo", ageTo);
		}

		Query query = em.createQuery(queryBuilder, Customer.class);

		params.forEach((key, value) -> {
			query.setParameter(key, value);
		});
		/*query.setFirstResult(0);
		query.setMaxResults(25);*/
		return (List<Customer>) query.getResultList();

//		return customerRepository.findByName(name);
	}

	public List<Customer> getByLoan(Integer totalAmount) {
		return customerRepository.findByLoanTotalAmount(totalAmount);
	}

	public List<Contact> getAllContacts(long customerId) {
		return customerRepository.getAllContacts(customerId);
	}

	public List<Contact> getSpecificContacts(long id, Contact.Type type) {
		return customerRepository.getSpecificContacts(id, type);
	}

	public Page<Object> getSpecificValue(long id, Contact.Type type, int page, int size) {
		return customerRepository.getSpecificValue(id, type, PageRequest.of(page, size));
	}

	public void uploadFile(long customerId, String filename, String fileLocation) {
		File f = new File();
		f.setCustomerId(customerId);
		f.setFileLocation(fileLocation);
		f.setFileName(filename);
		fileRepository.save(f);
	}

	public List<File> getFiles(long id) {
		return customerRepository.getFiles(id);
	}


}