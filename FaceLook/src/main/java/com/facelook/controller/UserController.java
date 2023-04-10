package com.facelook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facelook.entities.User;
import com.facelook.exceptions.UserException;
import com.facelook.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/facelook")
public class UserController {
	
	@Autowired
	UserService userService;

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/users")
	ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws UserException {
		return new ResponseEntity<User>(userService.registerUser(user), HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@GetMapping("/users/{id}")
	ResponseEntity<User> getUsersById(@PathVariable Long id) throws UserException {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@PutMapping("/users/{id}")
	ResponseEntity<User> updateUsersById(@PathVariable Long id, @RequestBody User user) throws UserException {
		return new ResponseEntity<User>(userService.updateUserById(id, user), HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@DeleteMapping("/users/{id}")
	ResponseEntity<String> deleetUsersById(@PathVariable Long id) throws UserException {
		return new ResponseEntity<String>(userService.deleteUserById(id), HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@GetMapping("/analytics/users")
	ResponseEntity<Long> getAllUsers() throws UserException {
		return new ResponseEntity<Long>(userService.getAllUsers(), HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@GetMapping("/analytics/users/top-active")
	ResponseEntity<List<User>> TopActiveUser() throws UserException {
		return new ResponseEntity<List<User>>(userService.Top5ActiveUser(), HttpStatus.FOUND);
	}
	
}
