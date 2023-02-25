package com.codewithdurgesh.blog.payloads;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer PostId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addDate;
    
    private CategoryDto categoryDto;
    
    private UserDto userDto;
    
	
	
}
