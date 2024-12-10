/**
 * @author Khanjinguur A.
 *
 * @description This repository interface provides CRUD operations for managing cart entities.
 * It extends JpaRepository to leverage built-in methods for entity persistence.
 * Additionally, it declares a custom query method to find a cart that has not been ordered.
 */

package com.ecommerce.repository;

import com.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository  extends JpaRepository<Cart, Long> {
    Cart findByIsOrderedFalse();
}