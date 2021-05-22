package com.example.consumer.service;

import com.example.consumer.entity.UserMaster;
import com.example.consumer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserMaster saveUser(UserMaster userMaster){
        return userRepository.save(userMaster);
    }



}
