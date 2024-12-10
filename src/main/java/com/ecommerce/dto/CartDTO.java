/**
 * @author Enkh-Amgalan G.
 *
 * @description This DTO class represents a shopping cart, including its ID, order status, and details of products within the cart.
 */

package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Represents a shopping cart with its ID, order status, and a list of cart details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Long id;
    private boolean isOrdered;
    private List<CartDetailDTO> cartDetails;
}