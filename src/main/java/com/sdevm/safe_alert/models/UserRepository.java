package com.sdevm.safe_alert.models;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByIdentifier(String identifier);
}
