package com.codewithdurgesh.blog.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private Integer categoryId ;
	private String categoryTitle;
	private String categoryDescription;
}
