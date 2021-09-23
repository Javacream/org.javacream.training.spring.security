package org.javacream.training.security.webservice;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SimpleWebService {

	@RequestMapping(value = "/anonymous", method = RequestMethod.GET)
	public ResponseEntity<String> getAnonymous() {
		return ResponseEntity.ok("Hello Anonymous");
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public ResponseEntity<String> getUser() {
		return ResponseEntity.ok("Hello User");
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> getAdmin() {
		return ResponseEntity.ok("Hello Admin");
	}

	@RequestMapping(value = "/all-user", method = RequestMethod.GET)
	public ResponseEntity<String> getAllUser() {
		return ResponseEntity.ok("Hello All User");
	}
}
