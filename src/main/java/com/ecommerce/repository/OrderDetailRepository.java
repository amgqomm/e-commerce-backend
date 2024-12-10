/**
 * @author Khanjinguur A.
 *
 * @description This repository interface provides CRUD operations for managing order detail entities.
 * It extends JpaRepository to leverage built-in methods for entity persistence.
 */

package com.ecommerce.repository;

import com.ecommerce.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}