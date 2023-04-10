package com.facelook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.facelook.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
//	@Query(value = "select * from Post p order by p.likes desc",nativeQuery = true)
//	List<Post> findTopLikedPost();
	
	List<Post> findTop5ByOrderByLikesDesc();
	
}
