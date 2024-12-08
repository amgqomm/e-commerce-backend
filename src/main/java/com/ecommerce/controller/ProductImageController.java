package com.ecommerce.controller;

import com.ecommerce.entity.ProductImage;
import com.ecommerce.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products-images")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @GetMapping
    public List<ProductImage> getAllProductsImages() {
        return productImageService.getAllProductsImage();
    }

    @PostMapping
    public ProductImage createProductImages(@RequestBody ProductImage productImages) {
        return productImageService.createProductImage(productImages);
    }
}
