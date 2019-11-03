package io.valley.school.dal.impl;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.valley.school.model.UserVO;

@Repository
public interface UserRepository extends MongoRepository<UserVO, String> {
	
	List<UserVO> findByFirstNameOrLastNameOrCityOrPostalCodeOrderByFirstNameAscAllIgnoreCase(String firstName,String lastName, String city, String postalCode);

}
