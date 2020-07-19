package com.spring.mvc.report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.mvc.report.exporter.ExcelFileExporter;
import com.spring.mvc.report.model.Customer;
import com.spring.mvc.report.service.CustomerService;

import net.bytebuddy.implementation.bind.annotation.FieldValue;


@Controller
public class DownloadExcelController {
	

	@Autowired
	CustomerService custService;
	
	@RequestMapping("/")
    public String index() {
		System.out.println("index");
        return "index";
    }
		
	@GetMapping("/download/customers.xlsx")
    public void downloadCsv(HttpServletResponse response, @RequestParam("start_date") String startDate, 
    		@RequestParam("end_date") String endDate) throws IOException {
		System.out.println("startDate : "+startDate+ " : "+ " EndDate"+ endDate);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=customers.xlsx");
        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(createTestData(startDate, endDate));
        IOUtils.copy(stream, response.getOutputStream());
    }

	private List<Customer> createTestData(String startDate, String endDate){
    	
    	return custService.getReport(startDate, endDate);
    }
}
