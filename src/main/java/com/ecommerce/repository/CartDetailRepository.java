/**
 * @author Khanjinguur A.
 *
 * @description This repository interface provides CRUD operations for managing cart details.
 * It extends JpaRepository to leverage built-in methods for entity persistence.
 * Additionally, it declares a custom query method to find a cart detail by its associated cart and product ID.
 */

package com.ecommerce.repository;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    Optional<CartDetail> findByCartAndProductId(Cart cart, Long productId);
}