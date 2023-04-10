package com.facelook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.facelook.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

	@Query(value = "select COUNT(*) from User")
	Long getAllUsers();
	
	@Query("SELECT u FROM User u JOIN u.posts p GROUP BY u ORDER BY COUNT(p) DESC")
    List<User> findTop5ByPostCount();

}