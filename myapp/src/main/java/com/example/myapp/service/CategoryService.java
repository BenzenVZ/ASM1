package com.example.myapp.service;

import com.example.myapp.model.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);
    public Category findById(Integer id);
    List<Category> findAll();
}
