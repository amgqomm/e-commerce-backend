/**
 * @author Enkh-Amgalan G.
 *
 * @description This entity class represents a shopping cart, which includes the cart ID, order status, and its associated cart details.
 */

package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isOrdered;

    @OneToMany(mappedBy = "cart")
    private List<CartDetail> cartDetails;
}