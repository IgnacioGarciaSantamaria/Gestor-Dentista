package com.IyJ.PracticaFinal.service.Implementation;

import java.util.ArrayList;
import java.util.List;

import com.IyJ.PracticaFinal.model.Users;
import com.IyJ.PracticaFinal.repository.UserRepository;
import com.IyJ.PracticaFinal.service.UserService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public Iterable<Users> retrieveAll() {
        return repository.findAll();
    }

    @Override
    public Users create(Users user) {
        user.setUserId(null);
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = repository.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        UserDetails newUser = new User(user.getUsername(), user.getPassword(), authorities);
        return newUser;
    }
}
