package com.example.producer.service;

import com.example.producer.entity.Cat;
import com.example.producer.entity.UserMaster;
import com.example.producer.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;


    public void generateData() {
        Faker faker = new Faker(Locale.US);
        for (int i = 1; i <= 100; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("firstName",faker.address().firstName());
            jsonObject.put("lastName",faker.address().lastName());
            jsonObject.put("fullAddress",faker.address().fullAddress());

            userRepository.save(new UserMaster(jsonObject.toJSONString()));
        }
    }


        public List<UserMaster> getAllData() {
            return userRepository.findAll();
        }
}
