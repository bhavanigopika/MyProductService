package com.ecommerce.productservicedecember.repositories;

import com.ecommerce.productservicedecember.models.Product;
import com.ecommerce.productservicedecember.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //what is the primary key of product is id whose data type is Long. So, apply as <Product, Long>
    //Product Repository contains all the methods(CRUD) related to the product model

    /*Declared Queries*/

    //select * from product where price > [some price which is provided by service] -> here the return type would be List<Product>
    List<Product> findProductByPriceGreaterThan(Double price);//jpa is implement this method internally and generate sql queries for this

    //select * from product where title like '%iphone' -> obviously return type is product
    List<Product> findProductByTitleLike(String title);

    //select * from product where title like 'iphone' LIMIT 5;
    //List<Product> findProductByTitleIsLike(int top, String title);

    //select * from product where price BETWEEN 10 and 25;
    List<Product> findByPriceBetween(Double lower, Double higher);

    //The below things are extended from crud repository. So no need to write findById, findAll,

    /*
    //to avoid null pointer exception, we use optional
    Optional<Product> findById(Long productId);//findById coming from crud repository, so we can directly apply in Services

    //get all products
    List<Product> findAll();//findAll coming from crud repository, so we can directly apply in Services

    //delete the single product by id
    void deleteById(Long aLong);deleteById coming from crud repository, so we can directly apply in Services

    //save the product
    Product save(Product p1);save coming from crud repository, so we can directly apply in Services

    */

    /*Hibernate Query Language - HQL*/

    //To define hql, then use query parameter. HQL - helpful to define more custom queries
    //refer Hibernate Query Language.docx
    //Here, Product is model not table, table we referred as product - Note down in HQL
    @Query("select p.id as id, p.title as title from Product p")
    List<ProductWithIdAndTitle> randomSearchMethodForProduct();

    /* Native Queries : Our case in native queries is SQL (i.e) we can write sql queries here as well*/

    //Here, we can write "product" referred as table not as captial 'P' Product. Just have to write like MySQL query. If we write in Oracle query,
    //then we have to write like it as Oracle Query here - Note down in Native Query
    @Query(nativeQuery = true, value = "select p.id as id, p.title as title from product p")
    List<ProductWithIdAndTitle> nativeSearchMethodForProduct();

    //to pass the parameter, we referred as p.id = :id
    @Query("select p.id as id, p.title as title from Product p where p.id = :id")
    ProductWithIdAndTitle randomSearchMethodForProduct(Long id);

}
