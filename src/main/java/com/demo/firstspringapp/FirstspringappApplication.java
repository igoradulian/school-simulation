package com.demo.firstspringapp;

import com.demo.firstspringapp.entity.Role;
import com.demo.firstspringapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstspringappApplication implements CommandLineRunner {

    @Autowired
	private RoleRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(FirstspringappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if(repository.count() > 0)
		{
			//do nothing
		}
		else
		{
			repository.save(new Role("ROLE_STUDENT"));
			repository.save(new Role("ROLE_PROFESSOR"));

		}
	}
}
