package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
