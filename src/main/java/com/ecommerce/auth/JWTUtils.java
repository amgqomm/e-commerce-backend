/**
 * @author Enkh-Amgalan G.
 *
 * @description Utility class for handling JSON Web Tokens (JWTs).
 * Provides methods for generating tokens, validating them, extracting claims, and managing token expiration.
 */

package com.ecommerce.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Utility class for JWT operations, including token creation, validation, and claim extraction.
 */
@Component
public class JWTUtils {

    /**
     * Initializes the secret key for signing JWT tokens.
     */
    private SecretKey key;
    private static final long EXPIRATION_TIME = 86400000;  // 24 hours

    public JWTUtils() {
        String secretString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * Generates a JWT token for the provided user details.
     *
     * @param userDetails the user's details.
     * @return a JWT token as a string.
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generates a refresh JWT token with custom claims.
     *
     * @param claims additional claims to include in the token.
     * @param userDetails the user's details.
     * @return a refresh JWT token as a string.
     */
    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extracts the username (subject) from the token.
     *
     * @param token the JWT token.
     * @return the username as a string.
     */
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    /**
     * Extracts specific claims from the token.
     *
     * @param token the JWT token.
     * @param claimsResolver a function to resolve specific claims.
     * @param <T> the type of the claim to extract.
     * @return the extracted claim.
     */
    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    /**
     * Validates the JWT token against the user details.
     *
     * @param token the JWT token.
     * @param userDetails the user's details.
     * @return true if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if the token has expired.
     *
     * @param token the JWT token.
     * @return true if the token has expired, false otherwise.
     */
    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}