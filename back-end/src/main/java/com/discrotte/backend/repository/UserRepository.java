package com.discrotte.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.discrotte.backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}