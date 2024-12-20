/**
 * @author Enkh-Amgalan G.
 *
 * @description This entity class represents a product in the e-commerce application,
 * containing details such as its name, price, category, and other attributes.
 */

package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String category;
    private Double rating;
    private String description;
    private String color;
    private Boolean stock;
    private String sku;
    private String code;
}