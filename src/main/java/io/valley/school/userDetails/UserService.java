package io.valley.school.userDetails;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<UserVO> getAllUsers(){
		List<UserVO> users = new ArrayList<>();
		userRepository.findAll().forEach(users :: add);
		return users;
	}
	
	public List<UserVO> getUsers(String firstName,String lastName, String city, String postalCode){
		List<UserVO> users = new ArrayList<>();
		userRepository.findByFirstNameOrLastNameOrCityOrPostalCodeOrderByFirstNameAscAllIgnoreCase(firstName,lastName,city,postalCode)
		.forEach(users :: add);
		return users;
	}
	
	public UserVO getUser(String id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public void addUser(UserVO user) {
		userRepository.save(user);
	}
}
