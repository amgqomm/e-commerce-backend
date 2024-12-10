/**
 * @author Enkh-Amgalan G.
 *
 * @description This DTO class represents the structure of request and response data,
 * including status code, error message, user credentials, token details, and user information.
 */

package com.ecommerce.dto;

import com.ecommerce.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestResponseDTO {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String city;
    private String role;
    private String email;
    private String password;
    private User ourUsers;
    private List<User> ourUsersList;
}