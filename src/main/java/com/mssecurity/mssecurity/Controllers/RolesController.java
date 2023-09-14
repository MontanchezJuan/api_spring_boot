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

import com.mssecurity.mssecurity.Models.Role;
import com.mssecurity.mssecurity.Repositories.RoleRepository;

@CrossOrigin
@RestController
@RequestMapping("api/roles")
public class RolesController {
    @Autowired
    private RoleRepository theRoleRepository;

    @GetMapping("")
    public List<Role> index() {
        return this.theRoleRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Role store(@RequestBody Role newRole) {
        return this.theRoleRepository.save(newRole);
    }

    @GetMapping("{id}")
    public Role show(@PathVariable String id) {
        Role theRole = this.theRoleRepository.findById(id).orElse(null);
        return theRole;
    }

    @PutMapping("{id}")
    public Role udpate(@PathVariable String id, @RequestBody Role theNewRole) {
        Role theActualRole = this.theRoleRepository.findById(id).orElse(null);
        if (theActualRole != null) {
            theActualRole.setName(theNewRole.getName());
            theActualRole.setDescription(theNewRole.getDescription());
            return this.theRoleRepository.save(theActualRole);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void destroy(@PathVariable String id) {
        Role theRole = this.theRoleRepository.findById(id).orElse(null);
        if (theRole != null) {
            this.theRoleRepository.delete(theRole);
        }
    }
}
