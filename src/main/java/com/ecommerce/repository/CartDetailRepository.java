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