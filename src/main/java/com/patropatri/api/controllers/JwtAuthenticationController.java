package com.patropatri.api.controllers;

import com.patropatri.api.models.AuthenticationDto;
import com.patropatri.api.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.patropatri.api.config.JwtTokenUtil;
import com.patropatri.api.models.JwtRequest;
import com.patropatri.api.models.JwtResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager mManager;

	@Autowired
	private JwtTokenUtil mJwtUtil;

	@Autowired
	private JwtUserDetailsService mService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = mService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = mJwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody AuthenticationDto item) throws Exception {
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
