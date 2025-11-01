package com.ladislau.crud_portfolio.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    public void testTokenValido() {
        String token = jwtUtil.generateToken("teste@email.com");
        assertTrue(jwtUtil.isTokenValid(token)); // <--- aqui estava validateToken
    }

    @Test
    public void testTokenInvalido() {
        String token = "tokenInvalido";
        assertFalse(jwtUtil.isTokenValid(token)); // <--- aqui também
    }
}
