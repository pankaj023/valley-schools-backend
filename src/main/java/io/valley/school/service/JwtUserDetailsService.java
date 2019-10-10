package io.valley.school.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("inside JwtUserDetailsService loadUserByUsername ########################## ");
		if ("pankaj".equals(username)) {
			System.out.println(" inside JwtUserDetailsServic if");
			return new User("pankaj", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			System.out.println(" inside JwtUserDetailsServic else");
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}