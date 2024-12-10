/**
 * @author Enkh-Amgalan G.
 *
 * @description This service class provides business logic for managing product images.
 * It uses the `ProductImageRepository` to interact with the database for CRUD operations on product images.
 */

package com.ecommerce.service;

import com.ecommerce.entity.ProductImage;
import com.ecommerce.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    /**
     * Retrieve all product images from the database.
     *
     * @return a list of all ProductImage objects.
     */
    public List<ProductImage> getAllProductImages() {
        return productImageRepository.findAll();
    }

    /**
     * Create a new product image and save it to the database.
     *
     * @param productImage The ProductImage object to be saved.
     * @return the saved ProductImage object.
     */
    public ProductImage createProductImage(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }
}

