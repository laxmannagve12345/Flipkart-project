package com.codewithdurgesh.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
private int id;

@NotEmpty
@Size(min=4,message="Username must be min 4 characters")
private String name;

@Email(message="Email address is not valid ....!!")
private String email;

@NotEmpty
@Size(min=3,max=10,message="passsword must be minimum of 3 char and max 10 char")

private String password;

@NotEmpty
private String about;
}
