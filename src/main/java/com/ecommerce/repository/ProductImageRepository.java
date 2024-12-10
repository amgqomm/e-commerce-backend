/**
 * @author Khanjinguur A.
 *
 * @description This repository interface provides CRUD operations for managing product image entities.
 * It extends JpaRepository to leverage built-in methods for entity persistence.
 * Additionally, it declares a custom method to find all images associated with a specific product ID.
 */

package com.ecommerce.repository;

import com.ecommerce.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findByProductId(Long productId);
}