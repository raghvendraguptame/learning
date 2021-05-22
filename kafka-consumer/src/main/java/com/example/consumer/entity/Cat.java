package com.example.consumer.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cat {

    @Id
    private Long catId;
    private String name;
    private String breed;
    private String registry;
}
