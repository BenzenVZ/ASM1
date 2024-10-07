package com.example.myapp.service.impl;

import com.example.myapp.model.Category;
import com.example.myapp.model.Cert;
import com.example.myapp.repository.CertRepository;
import com.example.myapp.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CertServiceImpl implements CertService {
    @Autowired
    private CertRepository certRepository;

    @Override
    public Cert save(Cert cert){
        return certRepository.save(cert);
    }

    @Override
    public void delete(String id){
        certRepository.deleteById(id);
    }

    @Override
    public void deleteById(String id) {
        certRepository.deleteById(id); // Call the repository to delete by ID
    }

    @Override
    public Page<Cert> findAll(Pageable pageable){
        return certRepository.findAll(pageable);
    }

    @Override
    public Page<Cert> findByCategory(Integer categoryId, Pageable pageable){
        Category category = new Category();
        category.setCategoryId(categoryId);
        return certRepository.findByCategory(categoryId, pageable);
    }
}
