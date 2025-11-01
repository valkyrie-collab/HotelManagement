package com.valkyrie.authentication.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.valkyrie.authentication.config.EntityFeignController;
import com.valkyrie.authentication.config.TokenConfig;
import com.valkyrie.authentication.model.User;
import com.valkyrie.authentication.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    private UserRepository userRepo;
    @Autowired
    private void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    private AuthenticationManager authenticationManager;
    @Autowired
    private void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private EntityFeignController feign;
    @Autowired
    private void setFeign(EntityFeignController feign) {this.feign = feign;}

    private TokenConfig config;
    @Autowired
    private void setConfig(TokenConfig config) {this.config = config;}

    public ResponseEntity<String> signUp(User user, boolean passwordChange) {

        if (userRepo.findById(user.getUsername()).orElse(null) != null && !passwordChange) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user already present...");
        }

        user.setRole("ROLE_" + user.getRole().toUpperCase()).setPassword(
            new BCryptPasswordEncoder(12).encode(user.getPassword())
        );

        userRepo.save(user);
        System.out.println("working");
        
        if (!passwordChange) {
            feign.addEntity(Base64.getEncoder().encodeToString(user.getUsername().getBytes()));
        }

        return userRepo.findById(user.getUsername()).orElse(null) == null? 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not saved...") : 
            ResponseEntity.status(HttpStatus.ACCEPTED).body("user saved successfully please login....");

    }

    public ResponseEntity<String> signIn(User user) {
        String token = null;
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            token = config.generateToken(user.getUsername(), authentication.getAuthorities());
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(token);

    }

    public ResponseEntity<User> getUser(String username) {
        User user = userRepo.findById(username).orElse(null);

        return user != null? 
            ResponseEntity.status(HttpStatus.OK).body(user) : 
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @Transactional
    public ResponseEntity<String> removeUser(String token) {
        String username = config.getUsername(token);

        if (!userRepo.existsById(username)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user already been removed...");
        }

        userRepo.deleteById(username);

        return userRepo.existsById(username)? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account not deleted") : 
            ResponseEntity.status(HttpStatus.OK).body("Account has been deleted successfully....");

    }

}
