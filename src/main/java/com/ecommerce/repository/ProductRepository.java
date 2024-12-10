/**
 * @authot Khanjinguur A.
 *
 * @description This repository interface provides CRUD operations for managing product entities.
 * It extends JpaRepository to leverage built-in methods for entity persistence.
 * Additionally, it declares a custom method to retrieve the top 6 products ordered by their rating in descending order.
 */

package com.ecommerce.repository;

import com.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop6ByOrderByRatingDesc();
}