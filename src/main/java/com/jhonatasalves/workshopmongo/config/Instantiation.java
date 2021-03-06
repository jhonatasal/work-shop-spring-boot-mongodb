package com.jhonatasalves.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jhonatasalves.workshopmongo.domain.Post;
import com.jhonatasalves.workshopmongo.domain.User;
import com.jhonatasalves.workshopmongo.dto.AuthorDTO;
import com.jhonatasalves.workshopmongo.dto.ComentDTO;
import com.jhonatasalves.workshopmongo.repository.PostRepository;
import com.jhonatasalves.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User jhonatas = new User(null, "Jhonatas Alves", "jhonatas@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob, jhonatas));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

		Post post3 = new Post(null, sdf.parse("23/12/2018"), "Hoje é o meu aniversario", "Acordei feliz hoje!",
				new AuthorDTO(jhonatas));

		ComentDTO c1 = new ComentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		ComentDTO c2 = new ComentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		ComentDTO c3 = new ComentDTO("É nos!", sdf.parse("22/03/2018"), new AuthorDTO(alex));

		post1.getComents().addAll(Arrays.asList(c1, c2));
		post2.getComents().add(c3);

		postRepository.saveAll(Arrays.asList(post1, post2, post3));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		jhonatas.getPosts().addAll(Arrays.asList(post3));

		userRepository.save(maria);
		userRepository.save(jhonatas);
	}

}
