package com.ecommerce.productservicedecember.services;

import com.ecommerce.productservicedecember.dto.FakeStoreProductDto;
import com.ecommerce.productservicedecember.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember.models.Category;
import com.ecommerce.productservicedecember.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {//mark rest template as bean
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);

        //throw new RuntimeException("This is an Exception. This is thrown from Product Service");

        //throw new ArithmeticException();//Goes to GlobalExceptionHandler and get the message

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("The product id " + productId + " does not exist");
        }

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    public Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();

        //convert fakeStoreProductDto to my Product
        product.setId(fakeStoreProductDto.getId());
        //responseType of category is String in fakeStore API, that's why I created as private String Category but for me,
        //my category is Category Type. So, I created a new object of category. In category class, I have created the constructor to initialize the object
        product.setCategory(new Category(fakeStoreProductDto.getCategory(), fakeStoreProductDto.getDescription()));

        //or
        //If I didn't create the constructor in category then I can set the name and description from fakeStoreProductDto
        /*
        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getDescription());
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        */

        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        return product;
    }

    @Override
    public List<Product> getAllProducts() {

        //List - ArrayList, LinkedList, Vector, Stack extends Vector
        //we want work like list but not generics, then we use Array, so instead of List<FakeStoreProductDto>, we can use array of FakeStoreProductDto[], so that no problem of generics will arise here
        //During run time, everything is object(raw type). FakeStoreProductDto is also converted to object during run time

        //List<FakeStoreProductDto> fspDtos = restTemplate.getForObject("https://fakestoreapi.com/products", List<FakeStoreProductDto>.class);

        //like int arr[] = new int[20];
        //size (say, 2o) comes from fake store API.
        FakeStoreProductDto fspDtos[] = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fspDtos){
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }

    //ToDo: check the update product: I've checked. Here, fakestore api not allowing the patch operation, even though the code has written
    //PATCH - partial update (i.e) specific value update - patch mapping
    @Override
    public Product updateProduct(Long id, Product product) {
        //PATCH
        /*
        //Here also patch object only refer but with detail, and you refer restTemplate.class(source code + documentation)
        a) RequestCallback requestCallback = this.httpEntityCallback(request, responseType);
        this - restTemplate
        request - object that I am passing (i.e) Product
        responseType - FakeStoreProductDto.class

        b) HttpMessageConverterExtractor<T> responseExtractor = new HttpMessageConverterExtractor(responseType, this.getMessageConverters());
        T - FakeStoreProductDto, this - restTemplate

        c) return (T)this.execute(url, HttpMethod.POST, requestCallback, responseExtractor, (Map)uriVariables);
        this - restTemplate, instead of post, use PATCH request
        */

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        //now, it returns fakeStoreProductDto because we mentioned, responseType would be FakeStoreProductDto.class
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);
        //we convert it into product
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

        //or use patchForObject
        /*request - object that I am passing (i.e) Product
          responseType - FakeStoreProductDto.class*/
        /*
        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.patchForObject("https://fakestoreapi.com/products" + id, product, FakeStoreProductDto.class);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto1);
        */
    }

    //PUT - update everything = put mapping, others gets null
    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        //now, it returns fakeStoreProductDto because we mentioned, responseType would be FakeStoreProductDto.class
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        //we convert it into product
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

    }

    @Override
    public void deleteSingleProduct(Long product_id) {
    }

    @Override
    public Product addNewProduct(Product product) {

        return null;
    }
}
