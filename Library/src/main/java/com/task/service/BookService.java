package com.task.service;

import java.util.List;

import com.task.exception.BookException;
import com.task.model.Book;

public interface BookService {

	public Book addBook(Book book) throws BookException;
	
	public  Book getBook(int id) throws BookException;
	
	public List<Book> getBooks();
	
	public Book updateBookDetails(int bid,Book book) throws BookException;
	
	public Book updateBookavailability(int bid,int quantity)throws BookException;
	
	public void deleteBook(int id)throws BookException;
	

}
