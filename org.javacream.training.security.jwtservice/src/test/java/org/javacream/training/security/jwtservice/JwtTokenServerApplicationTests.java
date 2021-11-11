package org.javacream.training.security.jwtservice;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.Claims;

@SpringBootTest
public class JwtTokenServerApplicationTests {

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
		System.out.println(token);
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
		final String USERNAME = "admin";
		UserDetails user = userDetailsService.loadUserByUsername(USERNAME);
		String token = jwtUtil.generateToken(user);
		String userName = jwtUtil.getUsernameFromToken(token);
		Assertions.assertEquals(USERNAME, userName);
	}

	@Test
	void tokenContainsRoles() {
		final String USERNAME = "admin";
		final String ROLE_NAME = "ROLE_USER";
		UserDetails user = userDetailsService.loadUserByUsername(USERNAME);
		String token = jwtUtil.generateToken(user);
		Claims claims = jwtUtil.getAllClaimsFromToken(token);
		System.out.println(claims);
		Assertions.assertTrue(((List<String>)claims.get("roles")).contains(ROLE_NAME));
	}

}