package com.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.exception.BookException;
import com.task.exception.UserException;
import com.task.model.Book;
import com.task.model.User;
import com.task.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private BookService bookService;
	
	@Override
	public User createUser(User user) throws UserException {
		// TODO Auto-generated method stub
		if(user==null)
			throw new UserException("Incomplete User Data");
		
		userRepository.save(user);
		return user;
	}

	@Override
	public User getUser(int uid) throws UserException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findById(uid);
		if(user.isEmpty())
			throw new UserException("User with id"+uid+" not found");
		return user.get();
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User updateUserDetails(int uid, User user) throws UserException {
		// TODO Auto-generated method stub
		User user1 = getUser(uid);
		user1.setFirstName(user.getFirstName());
		user1.setLastName(user.getLastName());
		userRepository.save(user1);
		return user1;
	}

	@Override
	public User updateUsersBook(int uid, int bid) throws UserException, BookException {
		// TODO Auto-generated method stub
		User user = getUser(uid);
		List<Book> books= user.getBooks();
		
		Book book = bookService.getBook(bid);
		if(books.contains(book)) {
			books.remove(book);
			bookService.updateBookavailability(bid,book.getQuantity()-1);
		}
		else {
			if(book.getQuantity()>=book.getTotal()) {
				throw new BookException("book not in stock");
			}
			books.add(book);
			bookService.updateBookavailability(bid,book.getQuantity()+1);
		}
		user.setBooks(books);
		userRepository.save(user);
		return user ;
	}

	@Override
	public void deleteUser(int uid) throws UserException, BookException {
		// TODO Auto-generated method stub
		User user = getUser(uid);
		List<Book> books = user.getBooks();
		while(!books.isEmpty()) {
			Book book = books.remove(books.size()-1);
			bookService.updateBookavailability(book.getId(), book.getQuantity()-1);
		}
		userRepository.deleteById(uid);
		
	}

}
