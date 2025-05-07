package com.task.service;

import java.util.List;

import com.task.exception.BookException;
import com.task.exception.UserException;
import com.task.model.Book;
import com.task.model.User;

public interface UserService {

	public User createUser(User user) throws UserException;
	
	public User getUser(int uid) throws UserException;
	
	public List<User> getUsers();
	
	public User updateUserDetails(int uid,User user) throws UserException;
	
	public User updateUsersBook(int uid,int bid) throws UserException, BookException;
	
	public void deleteUser(int uid)throws UserException, BookException;
}
