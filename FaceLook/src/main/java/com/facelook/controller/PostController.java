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

import com.facelook.entities.Post;
import com.facelook.exceptions.PostException;
import com.facelook.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/facelook")
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("/posts")
	@CrossOrigin(origins = "http://localhost:3000")
	ResponseEntity<Post> createPost(@Valid @RequestBody Post post) throws PostException {
		return new ResponseEntity<Post>(postService.createPost(post), HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@GetMapping("/posts/{id}")
	ResponseEntity<Post> getPostsById(@PathVariable Long id) throws PostException {
		return new ResponseEntity<Post>(postService.getPostById(id), HttpStatus.FOUND);
	}
	
	@CrossOrigin
	@PutMapping("/posts/{id}")
	ResponseEntity<Post> updateUsersById(@PathVariable Long id, @RequestBody Post post) throws PostException {
		return new ResponseEntity<Post>(postService.updatePostById(id, post), HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@DeleteMapping("/posts/{id}")
	ResponseEntity<String> deleetPostsById(@PathVariable Long id) throws PostException {
		return new ResponseEntity<String>(postService.deletePostById(id), HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@GetMapping("/posts/{id}/like")
	ResponseEntity<Integer> incrementLikes(@PathVariable Long id) throws PostException {
		return new ResponseEntity<Integer>(postService.incrementLikes(id), HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/posts/{id}/unlike")
	ResponseEntity<Integer> decrementLikes(@PathVariable Long id) throws PostException {
		return new ResponseEntity<Integer>(postService.decrementLikes(id), HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/analytics/posts")
	ResponseEntity<Integer> totalNoOfPost() throws PostException {
		return new ResponseEntity<Integer>(postService.totalPosts(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/analytics/posts/top-liked")
	ResponseEntity<List<Post>> topLikedPosts() throws PostException {
		return new ResponseEntity<List<Post>>(postService.topLikedPosts(), HttpStatus.FOUND);
	}
}
