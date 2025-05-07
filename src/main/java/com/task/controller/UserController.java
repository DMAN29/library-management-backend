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
import org.springframework.web.bind.annotation.RestController;

import com.task.exception.BookException;
import com.task.exception.UserException;
import com.task.model.User;
import com.task.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) throws UserException{
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userService.getUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<User> getUser(@PathVariable int uid) throws UserException{
		return new ResponseEntity<User>(userService.getUser(uid),HttpStatus.OK);
	}
	
	@PutMapping("/update/{uid}")
	public ResponseEntity<User> updateUser(@PathVariable int uid,@RequestBody User user) throws UserException{
		return new ResponseEntity<User>(userService.updateUserDetails(uid, user),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{uid}/book/{bid}")
	public ResponseEntity<User> updateBooks(@PathVariable int uid, @PathVariable int bid) throws UserException, BookException{
		return new ResponseEntity<User>(userService.updateUsersBook(uid, bid),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{uid}")
	public ResponseEntity<String> deleteUser(@PathVariable int uid) throws UserException, BookException{
		userService.deleteUser(uid);
		return new ResponseEntity<String>("Delete user with uid "+uid,HttpStatus.OK);
	}
}
