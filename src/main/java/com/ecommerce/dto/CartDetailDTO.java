/**
 * @author Enkh-Amgalan G.
 *
 * @description This DTO class represents the details of a product in a shopping cart.
 */
package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the details of a product in the cart, including product ID, name, and quantity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDTO {
    private Long productId;
    private String productName;
    private Double quantity;
}
