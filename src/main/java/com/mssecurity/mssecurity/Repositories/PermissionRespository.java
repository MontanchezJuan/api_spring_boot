package com.mssecurity.mssecurity.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mssecurity.mssecurity.Models.Permission;

public interface PermissionRespository extends MongoRepository<Permission, String> {

}
