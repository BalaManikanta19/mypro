package com.BookStore.BookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookStore.BookStore.Entity.Book;
import com.BookStore.BookStore.Repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository brepo;
	
	
	public void save(Book b) {
		brepo.save(b);
	}
	
	public List<Book> getAllBook(){
		return brepo.findAll();
	}
	public Book getBookById(int id) {
		return brepo.findById(id).get();
	}
}
