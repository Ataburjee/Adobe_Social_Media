package com.facelook.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Size(max = 50, min = 1)
	private String name;
	
	@Email(message = "Email should be a well formed.!")
	@Column(nullable = false)
	private String email;
	
	@Size(max = 200)
	private String bio;
	
	@Column(updatable = false)
	private LocalDateTime created_at = LocalDateTime.now();
	
	private LocalDateTime updated_at = LocalDateTime.now();
	
	@OneToMany
	@JoinColumn(name = "userid")
	@JsonIgnore
	private List<Post> posts;
	
}
