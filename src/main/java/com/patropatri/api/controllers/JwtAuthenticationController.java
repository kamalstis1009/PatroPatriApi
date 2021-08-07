package com.patropatri.api.controllers;

import com.patropatri.api.models.AuthenticationDto;
import com.patropatri.api.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager mManager;

	@Autowired
	private JwtUserDetailsService mService;

	//======================================================| SignIn
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<?> createAuthenticationToken(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
		authenticate(username, password);
		return ResponseEntity.ok(mService.getUserDetailsByUserAndPass(username, password));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			mManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	//======================================================| SignUp
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody AuthenticationDto item) throws Exception {
		return ResponseEntity.ok(mService.register(item));
	}


}
