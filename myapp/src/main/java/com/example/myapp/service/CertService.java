package com.example.myapp.service;

import com.example.myapp.model.Cert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertService {
    public Cert save(Cert cert);
    public void delete(String id);
    public void deleteById(String id);
    Page<Cert> findAll(Pageable pageable);
    Page<Cert> findByCategory(Integer categoryId, Pageable pageable);
}
