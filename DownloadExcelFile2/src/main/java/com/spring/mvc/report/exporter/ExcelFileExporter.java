package com.spring.mvc.report.exporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.persistence.Id;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.spring.mvc.report.model.Customer;

public class ExcelFileExporter {
	
	public static ByteArrayInputStream contactListToExcelFile(List<Customer> customers) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Customers");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        // Creating header
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Id");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Name");
	        cell.setCellStyle(headerCellStyle);

	        cell = row.createCell(2);
	        cell.setCellValue("Designation");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(3);
	        cell.setCellValue("Salary");
	        cell.setCellStyle(headerCellStyle);

	        cell = row.createCell(4);
	        cell.setCellValue("DOJ");
	        cell.setCellStyle(headerCellStyle);

	        // Creating data rows for each customer
	        for(int i = 0; i < customers.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(customers.get(i).getId());
	        	dataRow.createCell(1).setCellValue(customers.get(i).getName());
	        	dataRow.createCell(2).setCellValue(customers.get(i).getDesignation());
	        	dataRow.createCell(3).setCellValue(customers.get(i).getSalary());
	        	dataRow.createCell(4).setCellValue(customers.get(i).getDoj());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
