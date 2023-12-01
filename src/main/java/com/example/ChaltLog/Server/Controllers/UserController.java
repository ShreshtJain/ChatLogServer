package com.example.ChaltLog.Server.Controllers;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChaltLog.Server.Entity.User;
import com.example.ChaltLog.Server.Service.UserService;

@RestController
@RequestMapping("/v1/chatlogs")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	  private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    private static final SecureRandom random = new SecureRandom();

	    public static String generateRandomString(int length) {
	        StringBuilder stringBuilder = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	            int randomIndex = random.nextInt(ALPHANUMERIC.length());
	            char randomChar = ALPHANUMERIC.charAt(randomIndex);
	            stringBuilder.append(randomChar);
	        }

	        return stringBuilder.toString();
	    }

	 @PostMapping("/user")
	 public ResponseEntity<String> createUser() {
		 
		 int desiredLength = 16; // You can change this to the desired length of your alphanumeric string
	     String uID = generateRandomString(desiredLength);
	     
	     User user=new User(uID,null);
	     userService.createUser(user);

	     return ResponseEntity.ok(uID);
	 }
	
	 @GetMapping("/user")
	 public ResponseEntity<List<User>> getUsers() {
	     
		 List<User> list=userService.getAllUsers();
	     return ResponseEntity.ok(list);
	 }
	 
	 @GetMapping("/user/{userId}")
	 public ResponseEntity<User> getUser(@PathVariable String userId) {
	     
		 User user=userService.getUserById(userId);
	     return ResponseEntity.ok(user);
	 }
	
	 @DeleteMapping("/user")
	 public ResponseEntity<String> deleteUsers() {
	     
		 userService.deleteUsers();
		 return ResponseEntity.ok("Successfully Deleted!");
	 }
	
	 @DeleteMapping("/user/{userId}")
	 public ResponseEntity<String> deleteUser(@PathVariable String userId) {
		 userService.deleteUser(userId);
	     return ResponseEntity.ok("Successfully Deleted!");
	 }
}
