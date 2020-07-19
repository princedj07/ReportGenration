package com.spring.mvc.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.report.model.Customer;
import com.spring.mvc.report.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerrepo; 
	
	public List<Customer> getReport(String startDate, String endDate) {

		System.out.println("StartDate : "+startDate);
		System.out.println("EndDate : "+endDate);
		List<Customer> list = customerrepo.getReport(startDate, endDate);
		list.forEach(System.out::println);
		return list;
	}

}
