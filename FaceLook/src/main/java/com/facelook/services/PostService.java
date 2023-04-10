package com.facelook.services;

import java.util.List;

import com.facelook.entities.Post;
import com.facelook.exceptions.PostException;

import jakarta.validation.Valid;

public interface PostService {

	Post createPost(@Valid Post post) throws PostException;

	Post getPostById(Long id) throws PostException;

	Post updatePostById(Long id, Post post) throws PostException;

	String deletePostById(Long id) throws PostException;

	Integer incrementLikes(Long id) throws PostException;

	Integer decrementLikes(Long id) throws PostException;

	Integer totalPosts() throws PostException;

	List<Post> topLikedPosts() throws PostException;

}
