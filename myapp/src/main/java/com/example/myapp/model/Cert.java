package com.example.myapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Certs")
public class Cert implements Serializable {
    @Id
    @Size(max = 12, message = "id không quá 12 ký tự")
    @Column(name = "id", unique = true)
    private String certId;

    @Column(name = "cert_name")
    @Size(max = 255, message = "Tên không quá 255 ký tự")
    private String certName;

    @Column(name = "cost")
    @DecimalMax("5")
    @DecimalMin("1")
    private Double cost;

    // Nhiều cert cho 1 category
    @NotNull(message = "Danh mục không được để trống")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Override
    public String toString() {
        return "Cert{" +
                "certId='" + certId + '\'' +
                ", certName='" + certName + '\'' +
                ", cost=" + cost +
                '}';
    }
}
