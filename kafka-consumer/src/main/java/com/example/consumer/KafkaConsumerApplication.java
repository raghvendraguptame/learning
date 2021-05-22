package com.example.consumer;

import com.example.consumer.entity.Cat;
import com.example.consumer.entity.UserMaster;
import com.example.consumer.service.CatService;
import com.example.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@EnableKafka
@SpringBootApplication
public class KafkaConsumerApplication {

	@Autowired
	UserService userService;

	@Autowired
	CatService catService;

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

	@KafkaListener(topics = "userData",containerFactory = "kafkaListenerContainerFactoryUser",groupId = "group_user")
	public void consumeUserData(UserMaster userMaster) {
		System.out.println(userMaster);
		userService.saveUser(userMaster);
	}

	@KafkaListener(topics = "catData",containerFactory = "kafkaListenerContainerFactoryCat",groupId = "group_cat")
	public void consumeUserData(Cat cat) {
		System.out.println(cat);
		catService.saveCatData(cat);
	}

//	@KafkaListener(topics = "fake-data",groupId = "group_id")
//	public void consumeData(String msg) {
//		System.out.println("~~~~~"+msg);
//	}

}
