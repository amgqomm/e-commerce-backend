/**
 * @author Khanjiguur A.
 *
 * @description This controller manages operations related to product images,
 * including retrieving all product images and creating new product images.
 */
package com.ecommerce.controller;

import com.ecommerce.entity.ProductImage;
import com.ecommerce.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for handling product image-related requests.
 */
@RestController
@RequestMapping("/api/products-images")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    /**
     * Retrieves all product images.
     *
     * @return a list of ProductImage objects.
     */
    @GetMapping
    public List<ProductImage> getAllProductImages() {
        return productImageService.getAllProductImages();
    }

    /**
     * Creates a new product image.
     *
     * @param productImage the product image entity.
     * @return the created ProductImage.
     */
    @PostMapping
    public ProductImage createProductImages(@RequestBody ProductImage productImage) {
        return productImageService.createProductImage(productImage);
    }
}
