package com.construction.security.services;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.construction.models.ERole;
import com.construction.models.Roles;
import com.construction.models.User;
import com.construction.repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		
		for (Iterator<Roles> it = user.getRoles().iterator(); it.hasNext(); ) {
	        Roles roles =  it.next();
	        if (roles.getName().equals(ERole.ROLE_USER)) {
	            return UserDetailsImpl.buildUser(user);
	        }     
	    }

		return UserDetailsImpl.build(user);
	}

}