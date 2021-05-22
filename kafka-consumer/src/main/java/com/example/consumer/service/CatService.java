package com.example.consumer.service;


import com.example.consumer.entity.Cat;
import com.example.consumer.repository.CatRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CatService {

    @Autowired
    CatRepository catRepository;

    public Cat saveCatData(Cat cat){
        return catRepository.save(cat);
    }

    public void generateCatData() {
        Faker faker = new Faker(Locale.US);
        for (int i = 1; i <= 100; i++) {
            catRepository.save(new Cat(faker.number().randomNumber(),faker.cat().name(),faker.cat().breed(),
                    faker.cat().registry()));
        }
    }

    public List<Cat> getAllData() {
        return catRepository.findAll();
    }

}
