package com.example.producer.controller;

import com.example.producer.entity.Cat;
import com.example.producer.entity.UserMaster;
import com.example.producer.service.CatService;
import com.example.producer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("kafka")
public class Producer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private CatService catService;

    @Value("${kafka.topic.name}")
    String topicName;


    @GetMapping("/publish/user")
    public String postToTopic() {
        List<UserMaster> data = userService.getAllData();
        long startTime = System.currentTimeMillis();
        for (UserMaster userMaster : data) {
            kafkaTemplate.send(topicName, userMaster);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to push 100 rows to kafka topic is " + (endTime - startTime));

        return "Data published Successfully to topic : " + topicName;
    }

    @GetMapping("/publish/cat")
    public String postToCatTopic() {
        List<Cat> data = catService.getAllData();
        for (Cat cat : data) {
            System.out.println(cat.toString());
            kafkaTemplate.send("catData",cat);
        }
        return "Data published Successfully to topic : animal-data";
    }

    @GetMapping("/data/generate/user")
    public String generateUserData() {
        userService.generateData();
        return "User Data Generated Successfully";
    }

    @GetMapping("/data/generate/cat")
    public String generateCatData() {
        catService.generateCatData();
        return "Cat Data Generated Successfully";
    }




}
