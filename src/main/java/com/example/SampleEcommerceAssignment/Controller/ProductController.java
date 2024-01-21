package com.example.SampleEcommerceAssignment.Controller;

import com.example.SampleEcommerceAssignment.Entity.Product;
import com.example.SampleEcommerceAssignment.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String addProduct(@RequestBody Product product){

        return productService.addProduct(product);
    }
}
