package com.example.myapp.controller;

import com.example.myapp.dto.CertProjection;
import com.example.myapp.model.Cert;
import com.example.myapp.repository.CategoryRepository;
import com.example.myapp.service.CategoryService;
import com.example.myapp.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CertListController {
    @Autowired
    private CertService certService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CertProjection certProjection;

    @GetMapping("/cert/list")
    public String showCertPage(Model model, @RequestParam(required = false) Integer categoryId,
                                @RequestParam(required = false, defaultValue = "1") Integer currentPage,
                                @RequestParam(required = false, defaultValue = "5") Integer maxRecord) {
        Page<Cert> certPage = null;

        Pageable pageable = PageRequest.of(currentPage -1, maxRecord);

        if(categoryId == null){
            certPage = certService.findAll(pageable);
        } else {
            certPage = certService.findByCategory(categoryId, pageable);
        }

        model.addAttribute("certList", certPage.getContent());
        model.addAttribute("totalPage", certPage.getTotalPages());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("maxRecord", maxRecord);

        model.addAttribute("categories", categoryService.findAll());

        return "cert_list";
    }

    @PostMapping("/cert/delete")
    public String deleteCert(@RequestParam String certId) {
        certService.deleteById(certId);

        return "redirect:/cert/list";
    }

    // Hiển thị total từng chứng chỉ
    @GetMapping("/cert/classify")
    public String classifyCerts(Model model) {
        model.addAttribute("classifications", certProjection.countCertsByCategory());
        return "cert_classification";
    }
}
