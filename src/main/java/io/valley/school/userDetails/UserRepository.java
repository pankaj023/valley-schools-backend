package io.valley.school.userDetails;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserVO, String> {
	List<UserVO> findByFirstNameOrLastNameOrCityOrPostalCodeOrderByFirstNameAscAllIgnoreCase(String firstName,String lastName, String city, String postalCode);

}
