package com.patropatri.api.services;

import java.util.ArrayList;
import java.util.Optional;

import com.patropatri.api.config.jwt.JwtTokenUtil;
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

	public UserWithToken getUserDetailsByUserAndPass(JwtRequest request) {
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

	public Authentication register2(AuthenticationWithUserDto item) throws UsernameNotFoundException {
		Authentication auth = mRepository.getAuthenticateByUsername(item.getUsername());
		if (auth == null) {
			Authentication model = new Authentication();
			model.setUsername(item.getUsername());
			model.setPassword(mEncoder.encode(item.getPassword()));
			model.setRole("USER");
			Authentication response = mRepository.save(model);
			Optional<User> user = mUserRepository.findById(response.getId());
			if (!user.isPresent()) {
				User model2 = new User();
				model2.setFullName(item.getFullName());
				model2.setGender(item.getGender());
				model2.setBirthDate(item.getBirthDate());
				model2.setReligion(item.getReligion());
				model2.setCountry2(item.getCountry());
				model2.setPhoneNumber(item.getUsername());
				model2.setIsActive(false);
				model2.setIsApproved(false);
				model2.setCommentAmount(0);
				model2.setLikedAmount(0);
				model2.setViewerAmount(0);
				model2.setId(response.getId());
				model2.setCreatedBy(response.getId());
				mUserRepository.save(model2);
				return response;
			}
			return response;
		} else {
			throw new UsernameNotFoundException("This user is already registered: " + item.getUsername());
		}
	}



}