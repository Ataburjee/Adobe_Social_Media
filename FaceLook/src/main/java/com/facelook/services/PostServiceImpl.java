package com.facelook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facelook.entities.Post;
import com.facelook.exceptions.PostException;
import com.facelook.repository.PostRepository;

import jakarta.validation.Valid;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostRepository postRepository;

	@Override
	public Post createPost(@Valid Post post) throws PostException {
		if(post == null)
			throw new PostException("Please fill the blanks...!");
		return postRepository.save(post);
	}

	@Override
	public Post getPostById(Long id) throws PostException {
		Optional<Post> post = postRepository.findById(id);
		if(post.isEmpty())
			throw new PostException("User does not exists with id " + id);
		return post.get();
	}

	@Override
	public Post updatePostById(Long id, Post post) throws PostException {
		Optional<Post> getpost = postRepository.findById(id);
		
		if(getpost.isEmpty())
			throw new PostException("User does not exists with id " + id);
		
		getpost.get().setContent(post.getContent());
		
		return postRepository.save(getpost.get());
	}

	@Override
	public String deletePostById(Long id) throws PostException {
		Optional<Post> getpost = postRepository.findById(id);
		
		if(getpost.isEmpty())
			throw new PostException("User does not exists with id " + id);
		
		postRepository.delete(getpost.get());
		
		return "Post deleted successfully with id " + id;
	}

	@Override
	public Integer incrementLikes(Long id) throws PostException {
		Optional<Post> getpost = postRepository.findById(id);
		
		if(getpost.isEmpty())
			throw new PostException("User does not exists with id " + id);
		
		getpost.get().setLikes(getpost.get().getLikes()+1);
		
		postRepository.save(getpost.get());
		
		return getpost.get().getLikes();
	}

	@Override
	public Integer decrementLikes(Long id) throws PostException {
		Optional<Post> getpost = postRepository.findById(id);
		
		if(getpost.isEmpty())
			throw new PostException("User does not exists with id " + id);
		
		if(getpost.get().getLikes()==0) return 0;
		
		getpost.get().setLikes(getpost.get().getLikes()-1);
		
		postRepository.save(getpost.get());
		
		return getpost.get().getLikes();
	}

	@Override
	public Integer totalPosts() throws PostException {
		Integer size = postRepository.findAll().size();
		if(size==0) throw new PostException("No post exists...!");
		return size;
	}

	@Override
	public List<Post> topLikedPosts() throws PostException {
		return postRepository.findTop5ByOrderByLikesDesc();
	}

}
