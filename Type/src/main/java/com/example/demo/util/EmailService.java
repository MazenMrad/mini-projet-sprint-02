package com.example.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService implements EmailSender {

	private final JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

	public void sendEmail(String to, String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom(from);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new IllegalStateException("failed to send email");
		}
	}
}
