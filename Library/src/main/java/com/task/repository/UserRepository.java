package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
