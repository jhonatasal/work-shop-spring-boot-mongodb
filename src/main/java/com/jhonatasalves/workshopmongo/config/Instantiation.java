package com.jhonatasalves.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jhonatasalves.workshopmongo.domain.User;
import com.jhonatasalves.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User jhonatas = new User(null, "Jhonatas Alves", "jhonatas@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob, jhonatas));
	}

}
