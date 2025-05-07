package com.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.exception.BookException;
import com.task.model.Book;
import com.task.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book addBook(Book book) throws BookException {
		// TODO Auto-generated method stub
		if(book == null) {
			throw new BookException("Book Data is Null");
		}
		return bookRepository.save(book);
	}

	@Override
	public Book getBook(int id) throws BookException {
		// TODO Auto-generated method stub
		Optional<Book> book =bookRepository.findById(id);
		if(book.isEmpty()) {
			throw new BookException("Book With id not Found");
		}
		return book.get();
	}

	@Override
	public List<Book> getBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public Book updateBookDetails(int bid, Book book) throws BookException {
		// TODO Auto-generated method stub
		Book book1 = getBook(bid);
		book1.setBookName(book.getBookName());
		book1.setTotal(book.getTotal());
		return bookRepository.save(book1);
	}

	@Override
	public Book updateBookavailability(int bid, int quantity) throws BookException {
		// TODO Auto-generated method stub
		Book book = getBook(bid);
		book.setQuantity(quantity);
		
		return bookRepository.save(book);
	}

	@Override
	public void deleteBook(int id) throws BookException {
		// TODO Auto-generated method stub
		Book book = getBook(id);
		bookRepository.deleteById(id);
		
	}
	
	
}
