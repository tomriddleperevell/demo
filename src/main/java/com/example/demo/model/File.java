package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = 'files')
public class File {
	private long id;

	@Column(name = 'customer_id')
	private long customerId;

	@Column(name = 'file_name')
	private String fileName;

	@Column(name = 'file_location')
	private String fileLocation;


}
