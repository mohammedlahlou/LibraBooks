package com.rest.restApi.reposotiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.restApi.entities.Book;


public interface BookRepository extends JpaRepository<Book, Long>{

}
