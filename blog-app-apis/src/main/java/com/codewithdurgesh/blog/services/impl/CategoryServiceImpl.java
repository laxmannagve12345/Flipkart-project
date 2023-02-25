package com.codewithdurgesh.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.exception.ResourseNotFoundException;
import com.codewithdurgesh.blog.payloads.CategoryDto;
import com.codewithdurgesh.blog.repository.CategoryRepo;
import com.codewithdurgesh.blog.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
     @Autowired
	private CategoryRepo categoryRepo;
     
     @Autowired
     private ModelMapper modelMapper;
     
     
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addecat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addecat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(( ) -> new ResourseNotFoundException("Category","category Id ",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatecat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
								.orElseThrow(( )-> new ResourseNotFoundException("Category","category id ",categoryId));
this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(( )-> new ResourseNotFoundException("Category","Catgory Id ",categoryId));

		return this.modelMapper.map(cat, CategoryDto.class);
	}



	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto>catDtos=categories.stream().map(cat-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
