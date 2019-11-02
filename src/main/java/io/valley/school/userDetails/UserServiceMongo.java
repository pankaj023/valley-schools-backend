package io.valley.school.userDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.valley.school.model.UserVOMongo;

@Repository
public class UserServiceMongo {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private  UserRepositoryMongo userRepositoryMongo;
	
	public void addNewUser(UserVOMongo user) {
		userRepositoryMongo.save(user);
	}
	
	public UserVOMongo getUser(String id) {
		return userRepositoryMongo.findById(id).orElse(null);
	}
	
	
}
