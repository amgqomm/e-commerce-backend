/**
 * @author Khanjinguur A.
 *
 * @description This repository interface provides CRUD operations for managing user entities.
 * It extends JpaRepository to leverage built-in methods for entity persistence.
 * Additionally, it declares a custom method to find a user by their email.
 */

package com.ecommerce.repository;

import com.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}