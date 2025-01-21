package com.ecommerce.productservicedecember.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseModel{

    private String title;
    //@Nullable
    private Double price;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    public Product() {
    }

    public Product(String title, Double price, Category category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

}
