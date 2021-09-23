package org.javacream.training.security.webservice.client;

import javax.annotation.PostConstruct;

import org.springframework.security.kerberos.client.KerberosRestTemplate;
import org.springframework.stereotype.Component;

@Component
public class SimpleClient {

	@PostConstruct
	public void init() {
		KerberosRestTemplate restTemplate = new KerberosRestTemplate(null, "-");
		System.out.println("TOMCAT:");
		System.out.println(restTemplate.getForObject("http://localhost:8080/webservice/api/anonymous", String.class));
		System.out.println(restTemplate.getForObject("http://localhost:8080/webservice/api/user", String.class));
		try {
			restTemplate.getForObject("http://localhost:8080/webservice/api/admin", String.class);
		} catch (Exception e) {
			System.out.println("not authorized to access admin");
		}
		System.out.println("Spring Boot:");
		System.out.println(restTemplate.getForObject("http://localhost:9090/api/anonymous", String.class));
		System.out.println(restTemplate.getForObject("http://localhost:9090/api/user", String.class));
		try {
			restTemplate.getForObject("http://localhost:9090/api/admin", String.class);
		} catch (Exception e) {
			System.out.println("not authorized to access admin");
		}
	}
}
