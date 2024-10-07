package com.example.myapp.service.impl;

import com.example.myapp.model.Category;

import com.example.myapp.repository.CategoryRepository;
import com.example.myapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findById(Integer id){
        return categoryRepository.findById(id).orElse(null);
    }

}
