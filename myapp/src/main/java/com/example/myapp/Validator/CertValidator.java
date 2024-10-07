package com.example.myapp.Validator;

import com.example.myapp.model.Cert;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CertValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Cert.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Cert cert = (Cert) target;

        // kiem tra tieu de 'vietnam', 'hochiminh'
        if  (cert.getCertName().equals("vietnam || hochiminh")) {
            // thong bao loi
            errors.rejectValue("title", "Error100",
                    "Chứa ký tự cấm");
        }

        // kiem tra mo ta
        if (cert.getCategory().equals("a")) {
            errors.rejectValue("description", "Error101", "Loi");
        }

        // kiểm validator cho Certname
        if (cert.getCertName() == null || cert.getCertName().trim().isEmpty()) {
            errors.rejectValue("certName", "certName.empty", "Tên chứng chỉ không được để trống");
        }

        // kiểm tra validator cho Category
        if (cert.getCategory() == null) {
            errors.rejectValue("category", "category.null", "Danh mục không được để trống");
        }
    }

}
