package com.example.myapp.controller;

import com.example.myapp.Validator.CertValidator;
import com.example.myapp.exception.DataNotFoundException;
import com.example.myapp.model.Cert;
import com.example.myapp.repository.CategoryRepository;
import com.example.myapp.repository.CertRepository;
import com.example.myapp.service.CategoryService;
import com.example.myapp.service.CertService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CertFormController {
    @Autowired
    private CertRepository certRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CertService certService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CertValidator certValidator;

    @GetMapping("/cert/form")
    public String addNewCertForm(Model model){
        model.addAttribute("cert", new Cert());
        model.addAttribute("categoryList", categoryService.findAll());

        return "cert_form";
    }

    @GetMapping(value = "/cert/form/{certId}")
    public String showCertForm(Model model, @PathVariable(name = "certId") String certId){

        Optional<Cert> optionalCert = certRepository.findById(certId);

        if(!optionalCert.isPresent()) {
            throw new DataNotFoundException("Cert ID" + certId + "không tìm thấy");
        }
        model.addAttribute("cert", optionalCert.get());
        model.addAttribute("categoryList", categoryRepository.findAll());

        return "cert_form";
    }

    @PostMapping(value = "/cert/form")
    public String saveCert(@Valid @ModelAttribute("cert") Cert cert,
                               BindingResult bindingResult, Model model) {
        certValidator.validate(cert, bindingResult);

        if(bindingResult.hasErrors()) {
            model.addAttribute("cert", cert);
            model.addAttribute("categoryList", categoryRepository.findAll());
            return "cert_form";
        }


        certService.save(cert);

        // Redirect back to the cert list after saving
        return "redirect:/cert/list";
    }

    @GetMapping(value = "/cert/delete")
    public String deleteCert(@RequestParam String certId, Model model){
        certService.deleteById(certId);
        //sau khi xóa thì sẽ quay trở lại trang list
        return "redirect:/cert/list";
    }
}
