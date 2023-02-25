package com.codewithdurgesh.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
}
