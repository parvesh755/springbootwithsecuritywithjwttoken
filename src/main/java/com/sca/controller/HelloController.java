package com.sca.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sca.entity.User;
import com.sca.repository.UserRepository;

@RestController 
@RequestMapping("/api")

@EnableAutoConfiguration

public class HelloController {
   
	
	@Autowired
	public UserRepository userRepository;
	
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
			try{
			User users = userRepository.save(user);
			return new ResponseEntity<>(users, HttpStatus.OK);
		} 
		catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	
	
	@RequestMapping(value = "/getusers", method = RequestMethod.GET)
	  public ResponseEntity<List<User>> getAllTutorials() {
	    try {
	      List<User> users = new ArrayList<User>();

	      userRepository.findAll().forEach(users::add);
	     
	      if (users.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(users, HttpStatus.OK);
	    } 
	      catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	

	 @RequestMapping(value = "/updateusers/{id}", method = RequestMethod.PUT)
	  public ResponseEntity<User> updateTutorial(@PathVariable("id") Integer id, @RequestBody User user) {
	    Optional<User> userdata = userRepository.findById(id);

	    if (userdata.isPresent()) {
	      User _users = userdata.get();
	      _users.setFirstname(user.getFirstname());
	      _users.setLastname(user.getLastname());
	      _users.setEmail(user.getEmail());
	      _users.setPhone(user.getPhone());
	      _users.setSalary(user.getSalary());
	      return new ResponseEntity<>(userRepository.save(_users), HttpStatus.OK);
	    } 
	   else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 @DeleteMapping("/userdelete/{id}")
	  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id) {
	    try {
	      userRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      
	    } 
	    catch (Exception e) {
	    
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 
	 @GetMapping("/getuserbyid/{id}")
	  public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
	   Optional<User> userdata = userRepository.findById(id);

	    if (userdata.isPresent()) {
	      
	    	 return new ResponseEntity<>(userdata.get(), HttpStatus.OK);
	    } 
	    else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 
	 
	 @GetMapping("/users/firstname")
	  public ResponseEntity<List<User>> findByFirstname(String firstname) {
	    try {
	      List<User> users = userRepository.findByFirstname(firstname);

	      if (users.isEmpty()) {
	           
	    	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	         return new ResponseEntity<>(users, HttpStatus.OK);
	     } 
	    catch (Exception e) {
	    	
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }


	 @GetMapping("/users/email")
	  public ResponseEntity<List<User>> findByEmail(String email) {
	    try {
	      List<User> users = userRepository.findByEmail(email);

	      if (users.isEmpty()) {
	           
	    	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	         return new ResponseEntity<>(users, HttpStatus.OK);
	     } 
	    catch (Exception e) {
	    	
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }



	
	@GetMapping("/hello") 
      public String hello() { 
		System.out.println("7");
         return "hello"; 
	}
         
    
  @GetMapping("/helloguys") 
   public String helloguys() { 
   return "this is your first springboot with security application"; 
       
         
   } 
   
   
}