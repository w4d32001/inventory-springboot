package com.morph.app.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morph.app.dto.DtoCategory;
import com.morph.app.entity.TCategory;
import com.morph.app.repository.CategoryRepository;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public List<DtoCategory> getAll() {
    return this.categoryRepository.findAll().stream()
        .map(DtoCategory::new)
        .collect(Collectors.toList());
  }

  public boolean insert(DtoCategory dtoCategory){
    dtoCategory.setCategoryId(UUID.randomUUID().toString());
    dtoCategory.setCreatedAt(new Date());
    dtoCategory.setUpdatedAt(new Date());
    TCategory tCategory = new TCategory(dtoCategory);
    categoryRepository.save(tCategory);
    return true;
  }

  public boolean delete(String categoryId){
    if(categoryRepository.existsById(categoryId)){
      categoryRepository.deleteById(categoryId);
      return true;
    }else{
      return false;
    }
  }
  
  public boolean update(DtoCategory dtoCategory){
    dtoCategory.setUpdatedAt(new Date());
    return categoryRepository.findById(dtoCategory.getCategoryId()).map(tCategory -> {
    tCategory.setName(dtoCategory.getName());
    tCategory.setDescription(dtoCategory.getDescription());
    categoryRepository.save(tCategory);
    return true;
    }).orElse(false);
  }

}
