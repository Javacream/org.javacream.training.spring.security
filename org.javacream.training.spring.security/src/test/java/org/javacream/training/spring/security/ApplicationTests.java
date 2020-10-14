package org.javacream.training.spring.security;

import org.javacream.training.spring.security.util.jwt.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.Claims;

@SpringBootTest

class ApplicationTests {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtUtil jwtUtil;

	@Test
	void contextLoads() {
	}

	@Test
	void createToken() {
		UserDetails user = userDetailsService.loadUserByUsername("user");
		String token = jwtUtil.generateToken(user);
		Assertions.assertNotNull(token);
	}

	@Test
	void createdTokenIsValid() {
		UserDetails user = userDetailsService.loadUserByUsername("user");
		String token = jwtUtil.generateToken(user);
		jwtUtil.validateToken(token, user);
	}

	@Test
	void tokenContainsUser() {
		final String USERNAME = "user";
		UserDetails user = userDetailsService.loadUserByUsername(USERNAME);
		String token = jwtUtil.generateToken(user);
		String userName = jwtUtil.getUsernameFromToken(token);
		Assertions.assertEquals(USERNAME, userName);
	}

	@Test
	void tokenContainsRoles() {
		final String USERNAME = "user";
		UserDetails user = userDetailsService.loadUserByUsername(USERNAME);
		String token = jwtUtil.generateToken(user);
		Claims claims = jwtUtil.getAllClaimsFromToken(token);
		Assertions.assertEquals("user", claims.get("sub"));
	}

}
