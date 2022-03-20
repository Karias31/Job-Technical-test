package com.barousse.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.barousse.project.domain.User;
import com.barousse.project.service.IUserService;

/**
 * This is an application that can save a user in the database, and display his information.
 * @author Cyril
 *
 */


@SpringBootApplication
public class AtosTechnicalTestApplication implements CommandLineRunner{
	@Autowired
	private IUserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AtosTechnicalTestApplication.class, args);
	}

	
	
	@Override
	public void run(String... args) throws Exception {
		
	}
}
