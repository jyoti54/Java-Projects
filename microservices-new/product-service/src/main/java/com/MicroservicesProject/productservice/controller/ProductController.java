package com.MicroservicesProject.productservice.controller;

import com.MicroservicesProject.productservice.dto.ProductRequest;
import com.MicroservicesProject.productservice.dto.ProductResponse;
import com.MicroservicesProject.productservice.model.Product;
import com.MicroservicesProject.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){

        return productService.getAllProducts();
    }

}
