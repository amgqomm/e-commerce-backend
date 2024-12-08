package com.ecommerce.controller;

import com.ecommerce.dto.RequestResponseDTO;
import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<RequestResponseDTO> register(@RequestBody RequestResponseDTO reg){
        return ResponseEntity.ok(userService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<RequestResponseDTO> login(@RequestBody RequestResponseDTO req){
        return ResponseEntity.ok(userService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<RequestResponseDTO> refreshToken(@RequestBody RequestResponseDTO req){
        return ResponseEntity.ok(userService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<RequestResponseDTO> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<RequestResponseDTO> getUSerByID(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUsersById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<RequestResponseDTO> updateUser(@PathVariable Long userId, @RequestBody User reqres){
        return ResponseEntity.ok(userService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<RequestResponseDTO> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = userService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<RequestResponseDTO> deleteUSer(@PathVariable Long userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}