package com.example.courseWork.repository;

import com.example.courseWork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String login, String password);
    User findByUsername(String login);
}
