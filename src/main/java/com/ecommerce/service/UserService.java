/**
 * @author Enkh-Amgalan G.
 *
 * @description This service handles user-related operations such as registration, login, token refresh, and management of user data.
 * It interacts with the `UserRepository` and `JWTUtils` to perform these operations.
 */

package com.ecommerce.service;

import com.ecommerce.auth.JWTUtils;
import com.ecommerce.dto.RequestResponseDTO;
import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user with the provided registration details.
     *
     * @param registrationRequest The DTO containing registration details.
     * @return A DTO containing the status and message of the operation.
     */
    public RequestResponseDTO register(RequestResponseDTO registrationRequest){
        RequestResponseDTO resp = new RequestResponseDTO();

        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setCity(registrationRequest.getCity());
            user.setRole(registrationRequest.getRole());
            user.setName(registrationRequest.getName());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            User ourUsersResult = userRepository.save(user);
            if (ourUsersResult.getId()>0) {
                resp.setOurUsers((ourUsersResult));
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    /**
     * Authenticates a user and provides a JWT token upon successful login.
     *
     * @param loginRequest The DTO containing login details.
     * @return A DTO containing the status, token, refresh token, and user role.
     */
    public RequestResponseDTO login(RequestResponseDTO loginRequest){
        RequestResponseDTO response = new RequestResponseDTO();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /**
     * Refreshes the JWT token using the provided refresh token.
     *
     * @param refreshTokenRequest The DTO containing the refresh token.
     * @return A DTO containing the status, new token, refresh token, and expiration time.
     */
    public RequestResponseDTO refreshToken(RequestResponseDTO refreshTokenRequest){
        RequestResponseDTO response = new RequestResponseDTO();
        try{
            String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            User users = userRepository.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenRequest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    /**
     * Retrieves all users from the repository.
     *
     * @return A DTO containing the list of all users and the status of the operation.
     */
    public RequestResponseDTO getAllUsers() {
        RequestResponseDTO reqRes = new RequestResponseDTO();

        try {
            List<User> result = userRepository.findAll();
            if (!result.isEmpty()) {
                reqRes.setOurUsersList(result);
                reqRes.setStatusCode(200);
                reqRes.setMessage("Successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("No users found");
            }
            return reqRes;
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
            return reqRes;
        }
    }

    /**
     * Retrieves a user by ID.
     *
     * @param userId The ID of the user to be retrieved.
     * @return A DTO containing the user details and the status of the operation.
     */
    public RequestResponseDTO getUserById(Long userId) {
        RequestResponseDTO reqRes = new RequestResponseDTO();
        try {
            User userById = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not found"));
            reqRes.setOurUsers(userById);
            reqRes.setStatusCode(200);
            reqRes.setMessage("Users with id '" + userId + "' found successfully");
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRes;
    }

    /**
     * Deletes a user by ID.
     *
     * @param userId The ID of the user to be deleted.
     * @return A DTO indicating the status and result of the operation.
     */
    public RequestResponseDTO deleteUser(Long userId) {
        RequestResponseDTO reqRes = new RequestResponseDTO();
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                userRepository.deleteById(userId);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User deleted successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return reqRes;
    }

    /**
     * Updates an existing user.
     *
     * @param userId The ID of the user to be updated.
     * @param updatedUser The entity containing updated user details.
     * @return A DTO containing the updated user details and the status of the operation.
     */

    public RequestResponseDTO updateUser(Long userId, User updatedUser) {
        RequestResponseDTO reqRes = new RequestResponseDTO();
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setCity(updatedUser.getCity());
                existingUser.setRole(updatedUser.getRole());

                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {

                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                User savedUser = userRepository.save(existingUser);
                reqRes.setOurUsers(savedUser);
                reqRes.setStatusCode(200);
                reqRes.setMessage("User updated successfully");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }
        } catch (Exception e) {
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return reqRes;
    }

    /**
     * Retrieves user information for the logged-in user.
     *
     * @param email The email of the logged-in user.
     * @return A DTO containing the user details and the status of the operation.
     */
    public RequestResponseDTO getMyInfo(String email){
        RequestResponseDTO reqRes = new RequestResponseDTO();
        try {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                reqRes.setOurUsers(userOptional.get());
                reqRes.setStatusCode(200);
                reqRes.setMessage("successful");
            } else {
                reqRes.setStatusCode(404);
                reqRes.setMessage("User not found for update");
            }

        }catch (Exception e){
            reqRes.setStatusCode(500);
            reqRes.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return reqRes;
    }
}