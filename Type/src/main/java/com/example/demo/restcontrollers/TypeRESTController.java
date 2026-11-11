package com.example.demo.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Type;
import com.example.demo.repos.TypeRepository;

@RestController
@RequestMapping("/api/type")
@CrossOrigin("*")
public class TypeRESTController {

	@Autowired
	TypeRepository typeRepository;

	@GetMapping
	public List<Type> getAllTypes() {
		return typeRepository.findAll();
	}

	@GetMapping("/{id}")
	public Type getTypeById(@PathVariable("id") Long id) {
		return typeRepository.findById(id).get();
	}

	@PostMapping
	public Type createType(@RequestBody Type type) {
		return typeRepository.save(type);
	}
}
