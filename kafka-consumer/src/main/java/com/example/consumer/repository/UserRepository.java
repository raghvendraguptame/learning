package com.example.consumer.repository;

import com.example.consumer.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserMaster,String> {
}
