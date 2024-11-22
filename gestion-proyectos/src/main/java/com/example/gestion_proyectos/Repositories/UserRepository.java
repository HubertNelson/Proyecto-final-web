package com.example.gestion_proyectos.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion_proyectos.models.User; 

public interface UserRepository extends JpaRepository<User, Long> { 
    User findByUsername(String username); 
}