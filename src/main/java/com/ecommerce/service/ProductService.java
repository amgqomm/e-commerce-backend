/**
 * @author Enkh-Amgalan G.
 *
 * @description This service class handles business logic for managing products and their images.
 * It uses `ProductRepository` and `ProductImageRepository` to interact with the database for product and product image operations.
 */

package com.ecommerce.service;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.ProductImage;
import com.ecommerce.repository.ProductImageRepository;
import com.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    /**
     * Fetches the top-rated products from the database.
     *
     * @return a list of top-rated ProductDTOs.
     */
    public List<ProductDTO> getTopRatedProducts() {
        List<Product> products = productRepository.findTop6ByOrderByRatingDesc();
        List<ProductDTO> returnProducts = new ArrayList<>();
        for (Product product : products) {
            returnProducts.add(getProductDetails(product.getId()));
        }
        return returnProducts;
    }

    /**
     * Fetches all products from the database.
     *
     * @return a list of all ProductDTOs.
     */
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> returnProducts = new ArrayList<>();
        for (Product product : products) {
            returnProducts.add(getProductDetails(product.getId()));
        }
        return returnProducts;
    }

    /**
     * Saves a new product and its associated images to the database.
     *
     * @param productDTO the ProductDTO containing product details.
     * @return the saved Product entity.
     */
    @Transactional
    public Product saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setRating(productDTO.getRating());
        product.setDescription(productDTO.getDescription());
        product.setColor(productDTO.getColor());
        product.setStock(productDTO.getStock());
        product.setSku(productDTO.getSku());
        product.setCode(productDTO.getCode());
        product = productRepository.save(product);
        Set<ProductImage> images = new HashSet<>();
        if (productDTO.getImageUrls() != null) {
            for (String imageUrl : productDTO.getImageUrls()) {
                ProductImage image = new ProductImage();
                image.setImageUrl(imageUrl);
                image.setProductId(product.getId());
                images.add(image);
            }
            productImageRepository.saveAll(images);
        }
        return product;
    }

    /**
     * Retrieves the details of a specific product.
     *
     * @param productId the ID of the product.
     * @return a ProductDTO containing product details and associated image URLs.
     */
    public ProductDTO getProductDetails(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return null;
        }
        List<String> imageUrls = productImageRepository.findByProductId(productId).stream()
                .map(ProductImage::getImageUrl)
                .collect(Collectors.toList());
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        productDTO.setImageUrls(imageUrls);
        return productDTO;
    }
}
