package org.student.portalstypendialny.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.student.portalstypendialny.model.User;

public interface UserRepository extends MongoRepository<User,String> {

    User findByUsername(String username);
}
