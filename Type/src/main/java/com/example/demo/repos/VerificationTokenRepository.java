package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);
}
