package com.facelook.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facelook.entities.User;
import com.facelook.exceptions.UserException;
import com.facelook.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;

	@Override
	public User registerUser(@Valid User user) throws UserException {
		if(userRepo.findByEmail(user.getEmail()).isPresent())
			throw new UserException("Email alredy exists...!");
		return userRepo.save(user);
	}

	@Override
	public User getUserById(Long id) throws UserException {
		Optional<User> user = userRepo.findById(id);
		if(user.isEmpty())
			throw new UserException("User does not exists with id " + id);
		return user.get();
	}

	@Override
	public User updateUserById(Long id, User user) throws UserException{
		Optional<User> getuser = userRepo.findById(id);
		
		if(getuser.isEmpty())
			throw new UserException("User does not exists with id " + id);
		
		if(user==null) throw new UserException("Please fill the blanks...!");
		
		if(user.getName() != null) getuser.get().setName(user.getName());
		
		if(user.getBio() != null) getuser.get().setBio(user.getBio());
		
		getuser.get().setUpdated_at(LocalDateTime.now());
		
		return userRepo.save(getuser.get());
		
	}

	@Override
	public String deleteUserById(Long id) throws UserException {
		Optional<User> user = userRepo.findById(id);
		
		if(user.isEmpty())
			throw new UserException("User does not exists with id " + id);
		
		userRepo.delete(user.get());
		
		return "User deleted successfully with id " + id;
	}

	@Override
	public Long getAllUsers() {
		return userRepo.getAllUsers();
	}

	@Override
	public List<User> Top5ActiveUser() {
		return userRepo.findTop5ByPostCount();
	}

}
