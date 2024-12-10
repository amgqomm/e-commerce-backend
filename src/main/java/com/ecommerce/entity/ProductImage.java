/**
 * @author Enkh-Amgalan G.
 *
 * @description This entity class represents a product image in the e-commerce application,
 * containing details such as the image URL and the associated product ID.
 */

package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;
    private String imageUrl;
    private Long productId;
}
