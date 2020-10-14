package org.javacream.training.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/api/anonymous", "/login*", "/logout*").permitAll().anyRequest().authenticated().and()
		.formLogin().permitAll().and().logout().permitAll();	}

	@Bean
	@Override
	@SuppressWarnings("deprecation")
	protected UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("pwd").roles("USER").build();
		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("pwd").roles("USER", "ADMIN").build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	

}
