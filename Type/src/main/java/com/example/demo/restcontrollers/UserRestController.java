package com.example.demo.restcontrollers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.entities.User;
import com.example.demo.exception.ExpiredTokenException;
import com.example.demo.exception.InvalidTokenException;
import com.example.demo.exception.ErrorDetails;
import com.example.demo.register.RegistationRequest;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserRestController {

	@Autowired
	UserService userService;

	@GetMapping("all")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}

	@PostMapping("/register")
	public User register(@RequestBody RegistationRequest request) {
		return userService.registerUser(request);
	}

	@GetMapping("/verifyEmail/{token}")
	public User verifyEmail(@PathVariable("token") String token) {
		return userService.validateToken(token);
	}

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorDetails> handleInvalidTokenException(InvalidTokenException exception,
			WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"INVALID_TOKEN");

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ExpiredTokenException.class)
	public ResponseEntity<ErrorDetails> handleExpiredTokenException(ExpiredTokenException exception,
			WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"EXPIRED_TOKEN");

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
