package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

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