package com.spring.mvc.report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.mvc.report.exporter.ExcelFileExporter;
import com.spring.mvc.report.model.Customer;
import com.spring.mvc.report.repository.CustomerRepository;


@Controller
public class DownloadExcelController {
	
	@Autowired
	CustomerRepository customerrepo; 
	
	@RequestMapping("/")
    public String index() {
		System.out.println("index");
        return "index";
    }
		
	@GetMapping("/download/customers.xlsx")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");
        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(createTestData());
        IOUtils.copy(stream, response.getOutputStream());
    }

	private List<Customer> createTestData(){
    	
    	return customerrepo.findAll();
    }
}
