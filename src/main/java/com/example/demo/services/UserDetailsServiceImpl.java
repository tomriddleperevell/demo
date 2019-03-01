package com.example.demo.services;

import com.example.demo.model.Authority;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	//@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUserName(username);
		if (user == null) throw new UsernameNotFoundException(username);

		long userId = user.getId();
		List<Authority> userAuthority =  getAuthorities(userId);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Authority authority : userAuthority){
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
	}

	public List<Authority> getAuthorities(long userId){
		return userRepository.getAuthorities(userId);
	}
}
