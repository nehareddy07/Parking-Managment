package com.parking.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserRepository repository;

	@PostMapping
	public ResponseEntity<User> add(@RequestBody User user) {
		return new ResponseEntity<>(repository.save(user), HttpStatus.OK);
	}

	@GetMapping("login")
	public ResponseEntity<User> login(@RequestParam String license, @RequestParam String password) {
		return new ResponseEntity<>(repository.findByLicenseAndPassword(license, password), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}
}
