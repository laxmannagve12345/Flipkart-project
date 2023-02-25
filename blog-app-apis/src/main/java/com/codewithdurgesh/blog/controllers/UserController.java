package com.codewithdurgesh.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
	private UserService userService;
    
    @PostMapping("/")
    public ResponseEntity<UserDto>createUser( @Valid @RequestBody UserDto userDto){
    	UserDto createUserDto=this.userService.createUser(userDto);
    	return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>updateUser( @Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
    	UserDto updatedUser=this.userService.updateUser(userDto, uid);
    	return ResponseEntity.ok(updatedUser);
    }
	
    @DeleteMapping("/{userId}")
    public ResponseEntity<?>deteUser(@PathVariable("userId") Integer uid){
    	this.userService.deleteUser(uid);
    	return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Succesfully",true), HttpStatus.OK);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<UserDto>>getAllUser(){
    	return ResponseEntity.ok(this.userService.getAllUsers());
    }
    
    @GetMapping("/{UserId}")
    public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer UserId){
    	return ResponseEntity.ok(this.userService.getUserById(UserId));
    }
}
