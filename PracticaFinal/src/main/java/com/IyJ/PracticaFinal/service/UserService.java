package com.IyJ.PracticaFinal.service;

import com.IyJ.PracticaFinal.model.Users;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Iterable<Users> retrieveAll();
    Users create(Users user);
}
