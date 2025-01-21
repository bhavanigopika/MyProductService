package com.ecommerce.productservicedecember.services;

import com.ecommerce.productservicedecember.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;//return type would be Product
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product) throws ProductNotFoundException;
    Product replaceProduct(Long id, Product product) throws ProductNotFoundException;

    void deleteSingleProduct(Long product_id) throws ProductNotFoundException;


    Product addNewProduct(Product product);
}
