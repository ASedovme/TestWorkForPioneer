package me.sedov.TestWorkForPioneer.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "atlantida";
    private static final long EXPIRATION_TIME_MS = 86400000; // 1 день

    public static String generateToken(Long userId) {
        return Jwts.builder()
                .claim("USER_ID", userId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}