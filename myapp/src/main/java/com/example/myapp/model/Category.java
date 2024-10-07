package com.example.myapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.UniqueKey;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer categoryId;

    @Column(name = "name", unique = true)
    @Size(max = 255, message = "Ten khong qua 255 ky tu")
    @NotNull(message = "Phải có name")
    private String categoryName;

    @Column(name = "descriptions")
    @Size(max = 1000, message = "Không quá 1000 kí tự")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Cert> certList = new ArrayList<>();

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", certList=" + certList +
                '}';
    }
}
