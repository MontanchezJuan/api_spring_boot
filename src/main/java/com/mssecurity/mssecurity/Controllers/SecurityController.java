package com.mssecurity.mssecurity.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mssecurity.mssecurity.Models.User;
import com.mssecurity.mssecurity.Repositories.UserRepository;
import com.mssecurity.mssecurity.Services.EncryptionService;
import com.mssecurity.mssecurity.Services.JWTService;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private UserRepository theUserRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private EncryptionService EncryptionService;

    @PostMapping("/login")
    public String login(@RequestBody User theUser, final HttpServletResponse response) throws IOException {
        User actualUser = this.theUserRepository.getUserByEmail(theUser.getEmail());
        String token = "";
        if (actualUser != null
                && actualUser.getPassword().equals(EncryptionService.convertirSHA256(theUser.getPassword()))) {
            token = jwtService.generateToken(actualUser);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return token;
    }

    @PutMapping("/forgot-password")
    public User forgotPassword(@RequestBody User theUser, final HttpServletResponse response) throws IOException {
        User actualUser = this.theUserRepository.getUserByEmail(theUser.getEmail());

        // if (actualUser != null
        // &&
        // actualUser.getPassword().equals(EncryptionService.convertirSHA256(theUser.getPassword())))
        // {
        // actualUser.setPassword(EncryptionService.convertirSHA256(theUser.getPassword()));
        // return this.theUserRepository.save(actualUser);
        // } else {
        // return null;
        // }

        return theUser;
    }
}