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

    public List<ProductImage> getAllProductsImage() {
        return productImageRepository.findAll();
    }

    public ProductImage createProductImage(ProductImage productImages) {
        return productImageRepository.save(productImages);
    }
}

