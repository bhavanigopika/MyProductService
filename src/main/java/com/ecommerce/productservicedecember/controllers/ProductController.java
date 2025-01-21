package com.ecommerce.productservicedecember.controllers;

import com.ecommerce.productservicedecember.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember.models.Product;
import com.ecommerce.productservicedecember.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    //@Qualifier("${my.bean.qualifier}")
    ProductService productService;//instance(object created from a class) of the product service interface

    public ProductController(@Qualifier("dbProductService") ProductService productService) {
        this.productService = productService;
    }

    /*public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }*/

    //Another way to provide @Qualifier is application.properties(check in the configuration file) - so, provide variable name and use configuration file to add the qualifier
    @GetMapping(
            path = "/{id}", produces = "application/json"
    )
    public Product getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        //return productService.getSingleProduct(id);//return type is product here, I have mentioned directly here
        Product product = productService.getSingleProduct(id);
        return product;

       /* Product product = new Product("abc",2.5D, new Category("cat1","cat1 desc"));
        Product product1 = new Product();
        return product1;*/
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Product> getProductByIdAndCheckResponseEntity(@PathVariable("id") Long id) throws ProductNotFoundException {
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();//this will return list of productsList<Products>
    }

    //Get the status for get all products also
    @GetMapping("/status")
    public ResponseEntity<List<Product>> getAllProductsAndCheckResponseEntity() {
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long product_id) throws ProductNotFoundException {
        productService.deleteSingleProduct(product_id);
    }

    //partial update (i.e) specific value update (i.e) update whatever is provided - patch mapping
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product newProduct) throws ProductNotFoundException {
        return productService.updateProduct(id, newProduct);
    }


    //replace the row/object = put mapping
    //@RequestBody - converts received json to Product java object and here it returns a product
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.replaceProduct(id, product);
    }

    //create the product - post mapping
    @PostMapping()
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);

    }
}