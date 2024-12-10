/**
 * @author Khanjiguur A.
 *
 * @description This controller manages product-related operations, such as retrieving top-rated products,
 * fetching all products, creating new products, and fetching details of a specific product.
 */

package com.ecommerce.controller;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller for managing product-related requests.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    /**
     * Constructor-based dependency injection for ProductService.
     *
     * @param productService the ProductService instance.
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves the top-rated products.
     *
     * @return a list of top-rated products as ProductDTO objects.
     */
    @GetMapping("/top-rated")
    public List<ProductDTO> getTopRatedProducts() {
        return productService.getTopRatedProducts();
    }

    /**
     * Retrieves all available products.
     *
     * @return a list of all products as ProductDTO objects.
     */
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Creates a new product from the provided ProductDTO.
     *
     * @param productDTO the product data transfer object.
     * @return ResponseEntity with the created Product and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.saveProduct(productDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * Retrieves the details of a specific product by its ID.
     *
     * @param id the ID of the product.
     * @return a ProductDTO object containing the product details.
     */
    @GetMapping("/{id}")
    public ProductDTO getProductDetails(@PathVariable Long id) {
        return productService.getProductDetails(id);
    }
}