package com.mssecurity.mssecurity.Models;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document()
public class RolePermission {
    @Id
    private String _id;

    @DBRef
    private Role role;

    @DBRef
    private Permission permission;

    public RolePermission() {
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
