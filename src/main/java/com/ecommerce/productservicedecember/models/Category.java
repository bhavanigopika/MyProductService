package com.ecommerce.productservicedecember.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category"/*, fetch = FetchType.EAGER*/)
    private List<Product> products;//Here use case is -> for the specific category -> get all the products
    /* Note: 1) There is no possible to store list of products in category table. So, mapping table/lookup table created - category_product
             2) In product we have @ManyToOne mapping, many product have 1 category -> this is possible, so category_id created in product table
             3) Actually, mapping table created when we have m : m mapping.
             4) Here, we don't need category_product table, because I get the category_id in product table itself to get all the products for specific category by
                        select * from product where category_id = 3
             5) So, here problem is I want to fetch the product for specific category using only 20th line, but table category_product should not create to store data again
             6) For this case, I've used "mappedBy" - This is already mapped at somewhere, so use that attribute to get the data. Here, it is mapped at category(we've used category attribute) in product table,
                so, mention as mapped by = "category". Now, we don't have table category_product
    */
    /*
    product : category
    1 : 1
    m : 1
    -----
    m : 1 -> product to category || category to product -> 1 : m
    -----

     */
    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
