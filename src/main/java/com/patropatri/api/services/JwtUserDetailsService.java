package com.patropatri.api.services;

import java.util.ArrayList;
import java.util.Optional;

import com.patropatri.api.config.JwtTokenUtil;
import com.patropatri.api.models.*;
import com.patropatri.api.repositories.AuthenticationRepository;
import com.patropatri.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private JwtTokenUtil mJwtUtil;

	@Autowired
	private PasswordEncoder mEncoder;

	@Autowired
	private UserRepository mUserRepository;

	@Autowired
	private AuthenticationRepository mRepository;

	//======================================================| Login
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Authentication model = mRepository.getAuthenticateByUsername(username);
		if (model == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(model.getUsername(), model.getPassword(),new ArrayList<>());
	}

	public UserWithToken getUserDetailsByUserAndPass(AuthenticationDto request) {
		String token = mJwtUtil.generateToken(loadUserByUsername(request.getUsername()));
		if (token != null) {
			Authentication response = mRepository.getAuthenticateByUsername(request.getUsername());
			if (response != null && mEncoder.matches(request.getPassword(), response.getPassword())) {
				Optional<User> item = mUserRepository.findById(response.getId());
				UserWithToken model = new UserWithToken();
				if (item.isPresent()) {
					model.setToken(token);
					model.setAuth(response);
					model.setUser(item.get());
				} else {
					model.setAuth(response);
					model.setToken(token);
				}
				return model;
			}
			return null;
		}
		return null;
	}

	//======================================================| Registration
	public Authentication register(AuthenticationDto item) throws UsernameNotFoundException {
		Authentication auth = mRepository.getAuthenticateByUsername(item.getUsername());
		if (auth == null) {
			Authentication model = new Authentication();
			model.setUsername(item.getUsername());
			model.setPassword(mEncoder.encode(item.getPassword()));
			model.setRole("USER");
			return mRepository.save(model);
		} else {
			throw new UsernameNotFoundException("This user is already registered: " + item.getUsername());
		}
	}

}