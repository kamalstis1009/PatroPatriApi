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

import com.patropatri.api.models.JwtRequest;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/jwt")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager mManager;

	@Autowired
	private JwtUserDetailsService mService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest request) throws Exception {
		authenticate(request.getUsername(), request.getPassword());
		return ResponseEntity.ok(mService.getUserDetailsByUserAndPass(request));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody AuthenticationDto item) throws Exception {
		return ResponseEntity.ok(mService.register(item));
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
}
