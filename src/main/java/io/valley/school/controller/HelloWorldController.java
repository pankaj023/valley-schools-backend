package io.valley.school.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.valley.school.config.JwtTokenUtil;
import io.valley.school.userDetails.UserVO;

@RestController
public class HelloWorldController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, value="/hello")
	public String hello() {
		return "Hello World";
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST, value = "/removeToken")
	public void logout(@RequestBody UserVO user){
		String jwtToken = user.getFirstName().substring(7);
		jwtTokenUtil.removeToken(jwtToken);
			
	}

}
