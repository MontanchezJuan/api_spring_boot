package com.mssecurity.mssecurity.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mssecurity.mssecurity.Models.RolePermission;

public interface RolePermissionRespository extends MongoRepository<RolePermission, String> {

}
