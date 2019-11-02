package io.valley.school.userDetails;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.valley.school.model.UserVOMongo;


@Repository
public interface UserRepositoryMongo extends MongoRepository<UserVOMongo, String> {
}