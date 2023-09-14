package com.mssecurity.mssecurity.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mssecurity.mssecurity.Models.Permission;
import com.mssecurity.mssecurity.Models.Role;
import com.mssecurity.mssecurity.Models.RolePermission;
import com.mssecurity.mssecurity.Repositories.PermissionRespository;
import com.mssecurity.mssecurity.Repositories.RolePermissionRespository;
import com.mssecurity.mssecurity.Repositories.RoleRepository;

@CrossOrigin
@RestController
@RequestMapping("api/role-permissions")
public class RolePermissionsController {
    @Autowired
    private RolePermissionRespository theRolePermissionRepository;

    @Autowired
    private RoleRepository theRoleRepository;

    @Autowired
    private PermissionRespository thePermissionRepository;

    @GetMapping("")
    public List<RolePermission> index() {
        return this.theRolePermissionRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("role/{role_id}/permission/{permission_id}")
    public RolePermission store(@PathVariable String role_id, @PathVariable String permission_id) {
        Role theActualRole = this.theRoleRepository.findById(role_id).orElse(null);
        Permission theActualPermission = this.thePermissionRepository.findById(permission_id).orElse(null);
        if (theActualRole != null && theActualPermission != null) {
            RolePermission newRolePermission = new RolePermission();
            newRolePermission.setRole(theActualRole);
            newRolePermission.setPermission(theActualPermission);
            return this.theRolePermissionRepository.save(newRolePermission);
        } else {
            return null;
        }
    }

    @GetMapping("{id}")
    public RolePermission show(@PathVariable String id) {
        RolePermission theRolePermission = this.theRolePermissionRepository.findById(id).orElse(null);
        return theRolePermission;
    }

    @PutMapping("{id}/role/{role_id}/permission/{permission_id}")
    public RolePermission udpate(@PathVariable String id, @RequestBody RolePermission theNewRolePermission) {
        RolePermission theActualRolePermission = this.theRolePermissionRepository.findById(id).orElse(null);
        if (theActualRolePermission != null) {
            // theActualRolePermission.set(theNewRolePermission.getUrl());
            // theActualRolePermission.setMethod(theNewRolePermission.getMethod());
            // theActualRolePermission.setMenuItem(theNewRolePermission.getMenuItem());
            return this.theRolePermissionRepository.save(theActualRolePermission);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        RolePermission theRolePermission = this.theRolePermissionRepository.findById(id).orElse(null);
        if (theRolePermission != null) {
            this.theRolePermissionRepository.delete(theRolePermission);
        }
    }
}
