package com.example.courseWork.service;

import com.example.courseWork.entity.Role;
import com.example.courseWork.entity.User;
import com.example.courseWork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public boolean addUser(User user){
        User dbUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(dbUser!=null){
            return false;
        }
        user.setRoles(Collections.singleton(Role.ADMIN));

        userRepository.save(user);
        return true;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.findByUsername(username);
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("Не удалось найти данного пользователя");
        }
    }
}
