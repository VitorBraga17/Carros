package com.example.carros.data.repositories;

import com.example.carros.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

     User findBylogin(String login);
}
