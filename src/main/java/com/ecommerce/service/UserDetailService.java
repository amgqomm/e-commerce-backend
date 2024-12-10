/**
 * @author Enkh-Amgalan G.
 *
 * @description This service implements the UserDetailsService interface to provide user details
 * based on the email address used for authentication. It interacts with the `UserRepository`
 * to fetch user information from the database.
 */

package com.ecommerce.service;

import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads the user details by username.
     *
     * @param username the username of the user.
     * @return the UserDetails object containing user information.
     * @throws UsernameNotFoundException if no user is found with the given email.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow();
    }
}