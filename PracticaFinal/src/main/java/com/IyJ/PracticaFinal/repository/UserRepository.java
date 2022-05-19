package com.IyJ.PracticaFinal.repository;

import com.IyJ.PracticaFinal.model.Users;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
    public Users findByUsername(String username);
}
