package com.codewithdurgesh.blog.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name="categories")
public class Category {
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
     
     @Column(name="title",length=100,nullable=false)
	private String categoryTitle;
     
     @Column(name="description")
	private String categoryDescription;
}
