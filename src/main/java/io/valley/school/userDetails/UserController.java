package io.valley.school.userDetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.valley.school.config.JwtTokenUtil;
import io.valley.school.userDetails.UserVO;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	/*@CrossOrigin
	@RequestMapping("/users")
	public List<UserVO> getAllUsers() {
		return userService.getAllUsers();
	}*/
	
	@CrossOrigin
	@RequestMapping("/users")
	public List<UserVO> getUsers(@RequestParam("firstName") String firstName,
									@RequestParam("lastName") String lastName,
									@RequestParam("city") String city,
									@RequestParam("postalCode") String postalCode) {
		
		if((StringUtils.isEmpty(firstName) || "undefined".equals(firstName) || "null".equals(firstName)) 
				&& (StringUtils.isEmpty(lastName) || "undefined".equals(lastName) || "null".equals(lastName))
				&& (StringUtils.isEmpty(city) || "undefined".equals(city) || "null".equals(city))
				&& (StringUtils.isEmpty(postalCode) || "undefined".equals(postalCode) || "null".equals(postalCode))) {
			System.out.println("inside all users");
			return userService.getAllUsers();
		}
		return userService.getUsers(firstName, lastName, city, postalCode);
	}
	
	@CrossOrigin
	@RequestMapping("/users/{foo}")
	public UserVO getUser(@PathVariable("foo") String id) {
		return userService.getUser(id);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST, value="/users")
	public void addUser(@RequestBody UserVO user) {
		userService.addUser(user);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST, value = "/remo")
	public void logout(@RequestBody UserVO user){
		String jwtToken = user.getFirstName().substring(7);
		jwtTokenUtil.removeToken(jwtToken);
			
	}

}
