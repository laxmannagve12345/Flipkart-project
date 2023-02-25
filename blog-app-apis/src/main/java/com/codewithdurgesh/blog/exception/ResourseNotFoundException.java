package com.codewithdurgesh.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResourseNotFoundException extends RuntimeException{
	
String resourceName;
String fielName;
long fieldValue;
public ResourseNotFoundException(String resourceName, String fielName, long fieldValue) {
	super(String.format("%s not found with this name %s : %s", resourceName, fielName, fieldValue));
	this.resourceName = resourceName;
	this.fielName = fielName;
	this.fieldValue = fieldValue;
}

}
