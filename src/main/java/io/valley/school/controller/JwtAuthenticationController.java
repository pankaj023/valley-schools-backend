package io.valley.school.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.valley.school.config.JwtTokenUtil;
import io.valley.school.model.JwtRequest;
import io.valley.school.model.JwtResponse;
import io.valley.school.model.UserVO;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@RequestMapping(value = "/tokenremo", method = RequestMethod.POST)
	public void logout(@RequestBody UserVO user){
		String jwtToken = user.getFirstName();
		jwtTokenUtil.removeToken(jwtToken);
			
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		System.out.println("inside JwtAuthenticationController createAuthenticationToken");
		System.out.println("userName: " +authenticationRequest.getUsername() );
		System.out.println("password: " +authenticationRequest.getPassword() );
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		System.out.println("after authenticate_1");
		final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		System.out.println("after loadUserByUsername_2");
		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		System.out.println("inside JwtAuthenticationController authenticate");
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			System.out.println("inside USER_DISABLED ");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.println("inside INVALID_CREDENTIALS ");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
