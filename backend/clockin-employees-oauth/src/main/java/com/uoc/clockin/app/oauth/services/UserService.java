package com.uoc.clockin.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uoc.clockin.app.commons.models.entity.User;
import com.uoc.clockin.app.oauth.clients.UserFeignClient;

@Service
public class UserService implements IUserService, UserDetailsService {
	
	private Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = client.findByUsername(username);
		if(user == null) {
			log.error("Login error. User not found");
			throw new UsernameNotFoundException("Login error. User not found");
		}
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getType().name()))
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
				true, true, true, true, authorities);
	}

	@Override
	public User findByUsername(String username) {
		return client.findByUsername(username);
	}

}
