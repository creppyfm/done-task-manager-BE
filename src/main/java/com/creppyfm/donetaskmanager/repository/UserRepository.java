package com.creppyfm.donetaskmanager.repository;

import com.creppyfm.donetaskmanager.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserById(String id);
}
