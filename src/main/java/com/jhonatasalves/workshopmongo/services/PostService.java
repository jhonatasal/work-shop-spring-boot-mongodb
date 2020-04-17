package com.jhonatasalves.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonatasalves.workshopmongo.domain.Post;
import com.jhonatasalves.workshopmongo.domain.User;
import com.jhonatasalves.workshopmongo.dto.UserDTO;
import com.jhonatasalves.workshopmongo.repository.PostRepository;
import com.jhonatasalves.workshopmongo.repository.UserRepository;
import com.jhonatasalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	@Autowired
	private UserRepository repoUser;

	@Autowired
	private UserService serviceUser;

	public List<Post> findAll() {
		return repo.findAll();
	}

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Post postInsert(Post obj) {
		User user = serviceUser.findById(obj.getAuthor().getId());
		obj.getAuthor().setName(user.getName());
		user.getPosts().add(obj);
		repo.insert(obj);
		repoUser.save(user);
		return obj;
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public Post update(Post newObj) {
		Post obj = findById(newObj.getId());
		updateData(obj, newObj);
		return repo.save(obj);
	}

	private void updateData(Post obj, Post newObj) {
		obj.setTitle(newObj.getTitle());
		obj.setBody(newObj.getBody());
	}

}
