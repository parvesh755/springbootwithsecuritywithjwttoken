package com.sca.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sca.entity.Employee;

import com.sca.repository.EmployeeRepository;

@RestController 
@RequestMapping("/api")

@EnableAutoConfiguration

@Component
public class EmployeeController {
	
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	@RequestMapping(value = "/addemployee", method = RequestMethod.POST)
	public ResponseEntity<Employee> createUser(@Valid @RequestBody Employee employee)
	{
			try{
			Employee employees = employeeRepository.save(employee);
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} 
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	
	@RequestMapping(value = "/getemployees", method = RequestMethod.GET)
	  public ResponseEntity<List<Employee>> getAllTutorials() {
	    try {
	      List<Employee> employees = new ArrayList<Employee>();

	      employeeRepository.findAll().forEach(employees::add);
	     
	      if (employees.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(employees, HttpStatus.OK);
	    } 
	      catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	

	 @GetMapping("/getemployeebyempid/{empid}")
	  public ResponseEntity<Employee> getUserById(@PathVariable("empId") Integer empId) {
	   Optional<Employee> employeedata = employeeRepository.findById(empId);

	    if (employeedata.isPresent()) {
	      
	    	 return new ResponseEntity<>(employeedata.get(), HttpStatus.OK);
	    } 
	    else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	
	
}
