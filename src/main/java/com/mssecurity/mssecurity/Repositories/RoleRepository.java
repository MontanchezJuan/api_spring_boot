package com.mssecurity.mssecurity.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mssecurity.mssecurity.Models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

}
