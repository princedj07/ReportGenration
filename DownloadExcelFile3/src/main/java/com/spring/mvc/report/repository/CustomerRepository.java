package com.spring.mvc.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.mvc.report.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query(value = "SELECT * FROM employee where doj between ?1 And ?2 ", nativeQuery = true)
	List<Customer> getReport(String startDate, String endDate);

}
