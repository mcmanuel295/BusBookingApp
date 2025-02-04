package com.example.BusBookingApp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private String secretKeyString =" ";

    public JwtService() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGen.generateKey();
        secretKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }


    private SecretKey getKey(){
        byte[] secretKey= Base64.getDecoder().decode(secretKeyString);
        return Keys.hmacShaKeyFor(secretKey);
    }

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }



    private <T> T extractClaim(String token, Function<Claims,T> claimResolver){
       final Claims claims= extractAllClaims(token);
       return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(String username) {
        Map<String,Claims> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration( new Date(System.currentTimeMillis()+ (1000*60*60)))
                .and()
                .signWith(getKey())
                .compact();
    }

    public boolean validate(UserDetails userDetails, String token) {
        return !isTokenExpired(token) && userDetails.getUsername().equals(extractUsername(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token,Claims::getExpiration).before(new Date());
    }

}
