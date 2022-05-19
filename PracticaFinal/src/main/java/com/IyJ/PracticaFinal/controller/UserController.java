package com.IyJ.PracticaFinal.controller;

import com.IyJ.PracticaFinal.model.Users;
import com.IyJ.PracticaFinal.service.UserService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class  UserController {
    @Autowired
    UserService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public ResponseEntity<Iterable<Users>> retrieveAll(@AuthenticationPrincipal User user) {
        System.out.println(user.getUsername());
        return ResponseEntity.ok().body(service.retrieveAll());
    }

    @PostMapping("/users")
    public ResponseEntity<Users> create(@RequestBody Users user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        Users newUser = service.create(user);
        return ResponseEntity.ok().body(newUser);
    }
}
