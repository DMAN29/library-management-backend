package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.exception.BookException;
import com.task.model.Book;
import com.task.service.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("")
	public ResponseEntity<Book> addBook(@RequestBody Book book) throws BookException{
		return new ResponseEntity<Book>(bookService.addBook(book),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> getBooks(){
		return new ResponseEntity<>(bookService.getBooks(),HttpStatus.OK);
	}
	
	@GetMapping("/{bid}")
	public  ResponseEntity<Book> getBook(@PathVariable int bid) throws BookException{
		return new ResponseEntity<Book>(bookService.getBook(bid),HttpStatus.OK);
	}
	
	@PutMapping("/update/{bid}")
	public ResponseEntity<Book> updateBookData(@PathVariable int bid,@RequestBody Book book) throws BookException{
		return new ResponseEntity<Book>(bookService.updateBookDetails(bid, book),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{bid}")
	public ResponseEntity<String> deleteBook(@PathVariable int bid) throws BookException{
		bookService.deleteBook(bid);
		return new ResponseEntity<String>("Book with id"+ bid+" deleted",HttpStatus.ACCEPTED);
	}

}
