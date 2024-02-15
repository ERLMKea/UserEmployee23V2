package com.example.useremployee23v2.repository;

import com.example.useremployee23v2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
