package com.facelook.entities;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long userid;
	
	@Size(max = 300, min = 1, message = "Content should not exceed 300 char...!")
	private String content;
	
	@Column(updatable = false)
	private LocalDateTime created_at = LocalDateTime.now();
	
	private LocalDateTime updated_at = LocalDateTime.now();
	
	@Range(min = 0, message = "Likes can not be negetive")
	private Integer likes = 0;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
}
