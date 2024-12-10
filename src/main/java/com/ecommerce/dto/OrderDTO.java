/**
 * @author Enkh-Amgalan G.
 *
 * @description This DTO class represents an order, including the order ID, order date, order details, and address information.
 */
package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

/**
 * Represents an order with its ID, order date, details of ordered products, and address information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Date orderDate;
    private List<OrderDetailDTO> orderDetails;
    private String email;
    private String city;
    private String name;
    private String lastname;
    private String address;
    private String apartment;
    private String postalCode;
    private String country;
}