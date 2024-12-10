/**
 * @author Khanjinguur A.
 *
 * @description This repository interface provides CRUD operations for managing order entities.
 * It extends JpaRepository to leverage built-in methods for entity persistence.
 */

package com.ecommerce.repository;

import com.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}