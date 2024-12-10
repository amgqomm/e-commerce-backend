/**
 * @author Enkh-Amgalan G.
 *
 * @description This entity class represents an order in the system, including its details,
 * order date, and shipping information.
 */

package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;
    private String email;
    private String city;
    private String name;
    private String lastname;
    private String address;
    private String apartment;
    private String postalCode;
    private String country;
}
