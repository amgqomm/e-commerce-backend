/**
 * @authur Enkh-Amgalan G.
 *
 * @description This service class manages operations related to the shopping cart, including adding/removing items,
 * ordering items, and retrieving cart information. It uses various repositories to interact with the database.
 */

package com.ecommerce.service;

import com.ecommerce.dto.CartDTO;
import com.ecommerce.dto.CartDetailDTO;
import com.ecommerce.dto.OrderDTO;
import com.ecommerce.entity.*;
import com.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    /**
     * Retrieve the active cart. If no active cart exists, create a new one.
     *
     * @return the active Cart object.
     */
    public Cart getActiveCart() {
        Cart cart = cartRepository.findByIsOrderedFalse();
        if (cart == null) {
            cart = new Cart();
            cart.setOrdered(false);
            cartRepository.save(cart);
        }
        return cart;
    }

    /**
     * Add a product to the active cart.
     *
     * @param productId The ID of the product to add.
     * @param quantity  The quantity of the product to add.
     */
    public void addToCart(Long productId, Double quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Cart cart = getActiveCart();
        CartDetail cartDetail = new CartDetail();
        cartDetail.setProduct(product);
        cartDetail.setQuantity(quantity);
        cartDetail.setCart(cart);
        cartDetailRepository.save(cartDetail);
    }

    /**
     * Delete a product from the active cart.
     *
     * @param productId The ID of the product to delete.
     */
    public void deleteCartItem(Long productId) {
        Cart cart = getActiveCart();
        CartDetail cartDetail = cartDetailRepository.findByCartAndProductId(cart, productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartDetailRepository.delete(cartDetail);
    }

    /**
     * Place an order for the items in the active cart.
     *
     * @param orderDTO The details of the order to be placed.
     * @return the created Order object.
     */
    @Transactional
    public Order orderCart(OrderDTO orderDTO) {
        Cart cart = getActiveCart();
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setAddress(orderDTO.getAddress());
        order.setCity(orderDTO.getCity());
        order.setName(orderDTO.getName());
        order.setEmail(orderDTO.getEmail());
        order.setApartment(orderDTO.getApartment());
        order.setLastname(orderDTO.getLastname());
        order.setPostalCode(orderDTO.getPostalCode());
        order.setCountry(orderDTO.getCountry());
        order = orderRepository.save(order);

        Order finalOrder = order;
        List<OrderDetail> orderDetails = cart.getCartDetails().stream().map(cartDetail -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(finalOrder);
            orderDetail.setProduct(cartDetail.getProduct());
            return orderDetail;
        }).collect(Collectors.toList());

        orderDetailRepository.saveAll(orderDetails);

        cart.setOrdered(true);
        cartRepository.save(cart);
        return order;
    }

    /**
     * Retrieve the active cart as a CartDTO.
     *
     * @return the CartDTO representation of the active cart.
     */
    public CartDTO getActiveCartDTO() {
        Cart cart = getActiveCart();
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setOrdered(cart.isOrdered());

        List<CartDetailDTO> cartDetailDTOs = cart.getCartDetails().stream()
                .map(cartDetail -> {
                    CartDetailDTO cartDetailDTO = new CartDetailDTO();
                    cartDetailDTO.setProductId(cartDetail.getProduct().getId());
                    cartDetailDTO.setProductName(cartDetail.getProduct().getName());
                    cartDetailDTO.setQuantity(cartDetail.getQuantity());
                    return cartDetailDTO;
                })
                .collect(Collectors.toList());

        cartDTO.setCartDetails(cartDetailDTOs);
        return cartDTO;
    }
}
