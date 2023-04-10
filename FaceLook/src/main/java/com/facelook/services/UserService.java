package com.facelook.services;

import java.util.List;

import com.facelook.entities.User;
import com.facelook.exceptions.UserException;

import jakarta.validation.Valid;

public interface UserService {
	
	User registerUser(@Valid User user) throws UserException;
	
	User getUserById(Long id) throws UserException;
	
	User updateUserById(Long id, User user) throws UserException;

	String deleteUserById(Long id) throws UserException;

	Long getAllUsers();

	List<User> Top5ActiveUser();
	
}
