package com.example.myapp.controller;

import com.example.myapp.model.Category;
import com.example.myapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/list")
    public String listCategories(Model model,
                                  @RequestParam(required = false, defaultValue = "1") Integer currentPage,
                                  @RequestParam(required = false, defaultValue = "5") Integer maxRecord) {

        Pageable pageable = PageRequest.of(currentPage - 1, maxRecord);
        Page<Category> categoryPage = categoryService.findAll(pageable);

        model.addAttribute("categoryList", categoryPage.getContent());
        model.addAttribute("totalPage", categoryPage.getTotalPages());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("maxRecord", maxRecord);

        return "category_list";
    }
}
