package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entities.Book;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class TypeApplication implements CommandLineRunner {

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(TypeApplication.class, args);
	}

	@PostConstruct
	void init_users() {
		userService.addRole(new Role(null, "ADMIN"));
		userService.addRole(new Role(null, "USER"));

		userService.saveUser(new User(null, "admin", "123", null, true, null));
		userService.saveUser(new User(null, "user", "123", null, true, null));
		userService.saveUser(new User(null, "yassine", "123", null, true, null));

		userService.addRoleToUser("admin", "ADMIN");
		userService.addRoleToUser("admin", "USER");

		userService.addRoleToUser("user", "USER");
		userService.addRoleToUser("yassine", "USER");
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Book.class);
	}
}
