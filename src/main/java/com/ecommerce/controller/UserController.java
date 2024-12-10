/**
 * @author Khanjiguur A.
 *
 * @description This controller manages user-related operations, including registration,
 * login, token refresh, retrieving all users, retrieving a user by ID, updating a user,
 * fetching the authenticated user's profile, and deleting a user.
 */

package com.ecommerce.controller;

import com.ecommerce.dto.RequestResponseDTO;
import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling user-related requests.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Handles user registration.
     *
     * @param reg the registration details.
     * @return ResponseEntity with the registration response.
     */
    @PostMapping("/auth/register")
    public ResponseEntity<RequestResponseDTO> register(@RequestBody RequestResponseDTO reg){
        return ResponseEntity.ok(userService.register(reg));
    }

    /**
     * Handles user login.
     *
     * @param req the login request details.
     * @return ResponseEntity with the login response.
     */
    @PostMapping("/auth/login")
    public ResponseEntity<RequestResponseDTO> login(@RequestBody RequestResponseDTO req){
        return ResponseEntity.ok(userService.login(req));
    }

    /**
     * Refreshes the authentication token.
     *
     * @param req the token refresh request.
     * @return ResponseEntity with the refreshed token response.
     */
    @PostMapping("/auth/refresh")
    public ResponseEntity<RequestResponseDTO> refreshToken(@RequestBody RequestResponseDTO req){
        return ResponseEntity.ok(userService.refreshToken(req));
    }

    /**
     * Retrieves all users.
     *
     * @return ResponseEntity with the list of all users.
     */
    @GetMapping("/admin/get-all-users")
    public ResponseEntity<RequestResponseDTO> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());

    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user.
     * @return ResponseEntity with the user details.
     */
    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<RequestResponseDTO> getUserByID(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));

    }

    /**
     * Updates a user's information.
     *
     * @param userId the ID of the user.
     * @param userInfo the user details to update.
     * @return ResponseEntity with the updated user response.
     */
    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<RequestResponseDTO> updateUser(@PathVariable Long userId, @RequestBody User userInfo){
        return ResponseEntity.ok(userService.updateUser(userId, userInfo));
    }

    /**
     * Fetches the authenticated user's profile.
     *
     * @return ResponseEntity with the authenticated user's profile.
     */
    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<RequestResponseDTO> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        RequestResponseDTO response = userService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user.
     * @return ResponseEntity with the deletion response.
     */
    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<RequestResponseDTO> deleteUSer(@PathVariable Long userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}