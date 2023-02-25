package com.codewithdurgesh.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exception.ResourseNotFoundException;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;
import com.codewithdurgesh.blog.repository.CategoryRepo;
import com.codewithdurgesh.blog.repository.PostRepo;
import com.codewithdurgesh.blog.repository.UserRepo;
import com.codewithdurgesh.blog.services.PostService;
@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourseNotFoundException("User ","User Id",userId));
		
		Category category=this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourseNotFoundException("Category ","category Id",categoryId));

		Post post=this.modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourseNotFoundException("Post","post id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
 
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourseNotFoundException("Post","post id",postId));
	     this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost( Integer pageNumber,Integer pageSize) {


		Pageable p= PageRequest.of(pageNumber, pageSize);
		
		Page<Post> pagePost=this.postRepo.findAll(p);
		
		List<Post>allPosts=pagePost.getContent();
		
		List<PostDto>postDtos=allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		 
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());



		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourseNotFoundException("Post","post id",postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		 Category cat=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourseNotFoundException("Category","category id",categoryId));
		 List<Post>posts=this.postRepo.findByCategory(cat);
		 
		List<PostDto>postDtos= posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		 User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User","user id",userId));
		 List<Post> posts=this.postRepo.findByUser(user);
		 
		List<PostDto>postDtos= posts.stream().map((post)->this.modelMapper.map(posts,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
