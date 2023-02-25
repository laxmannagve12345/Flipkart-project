package com.codewithdurgesh.blog.services;

import java.util.List;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostByCategory(Integer CategoryId);
	
	List<PostDto> getPostByUser(Integer UserId);
	
	List<Post>searchPost(String keyword);

	
	
	
	
}
