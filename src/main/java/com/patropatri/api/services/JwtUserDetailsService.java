package com.patropatri.api.services;

import java.util.ArrayList;

import com.patropatri.api.models.Authentication;
import com.patropatri.api.models.AuthenticationDto;
import com.patropatri.api.repositories.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthenticationRepository mRepository;

	@Autowired
	private PasswordEncoder mEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Authentication model = mRepository.getAuthenticateByUsername(username);
		if (model == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(model.getUsername(), model.getPassword(),new ArrayList<>());
	}

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