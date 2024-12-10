/**
 * @author Khanjiguur A.
 *
 * @description This controller handles operations related to the shopping cart,
 * including retrieving the active cart, adding items, placing orders, and removing items.
 */
package com.ecommerce.controller;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.dto.OrderDTO;
import com.ecommerce.entity.Order;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling shopping cart-related requests.
 */
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * Retrieves the active shopping cart for the current user.
     *
     * @return the active cart as a CartDTO object.
     */
    @GetMapping("/active")
    public CartDTO getActiveCart() {
        return cartService.getActiveCartDTO();
    }

    /**
     * Adds a product to the shopping cart.
     *
     * @param productId the ID of the product to add.
     * @param quantity  the quantity of the product to add.
     */
    @GetMapping("/add")
    public void addToCart(@RequestParam Long productId, @RequestParam Double quantity) {
        cartService.addToCart(productId, quantity);
    }

    /**
     * Places an order for the current cart.
     *
     * @param orderDTO the order details.
     * @return ResponseEntity with HTTP status CREATED if the order is successful, or INTERNAL_SERVER_ERROR if there is an issue.
     */
    @PostMapping("/order")
    public ResponseEntity<Order> orderCart(@RequestBody OrderDTO orderDTO) {
        try {
            cartService.orderCart(orderDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a specific item from the shopping cart.
     *
     * @param productId the ID of the product to remove.
     */

    @DeleteMapping("/item/{productId}")
    public void deleteCartItem(@PathVariable Long productId) {
        cartService.deleteCartItem(productId);
    }
}
