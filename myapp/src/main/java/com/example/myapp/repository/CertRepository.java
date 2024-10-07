package com.example.myapp.repository;

import com.example.myapp.model.Cert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertRepository extends JpaRepository<Cert, String> {
    Page<Cert> findByCategory(Integer categoryId, Pageable pageable);

    // Đếm số cert từng category
    @Query(value = """
            SELECT c.categoryName, COUNT(ce)
            FROM Cert ce JOIN ce.category c
            GROUP BY c.categoryName
            """)
    List<Object[]> countCertsByCategory();
}
