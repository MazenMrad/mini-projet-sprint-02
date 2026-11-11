package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.register.RegistationRequest;

public interface UserService {

	User saveUser(User user);

	User findUserByUsername(String username);

	Role addRole(Role role);

	User addRoleToUser(String username, String rolename);

	List<User> findAllUsers();

	User registerUser(RegistationRequest request);

	User validateToken(String code);
}
