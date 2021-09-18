package com.rest.restApi.entities;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class Book {

	@Id
	@NotNull
	@Column(name = "id" ,nullable = false,updatable = false)
	private Long Id;
	
	@NotNull
	@Size(max = 64,message = "max is 64 alpha")
	@Column(name = "author", nullable = false)
	private String author;
	
	@NotNull(message = "not null")
	@Size(max = 64,message = "max is 64 alpha")
	@Column(name = "title", nullable = false)
	private String title;

	public Book() {
		
	}
	
	public Book(@NotNull Long id, @NotNull @Size(max = 64) String author, @NotNull @Size(max = 64) String title) {
		super();
		Id = id;
		this.author = author;
		this.title = title;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [Id=" + Id + ", author=" + author + ", title=" + title + "]";
	}

	
	
}
