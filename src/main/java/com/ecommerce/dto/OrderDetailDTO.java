/**
 * @author Enkh-Amgalan G.
 *
 * @description This DTO class represents the details of an order, including the order ID, product ID, and product name.
 */

package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the details of an order, including the order ID, product ID, and product name.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private Long productId;
    private String productName;
}