package com.jhonatasalves.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jhonatasalves.workshopmongo.domain.Post;
import com.jhonatasalves.workshopmongo.domain.User;
import com.jhonatasalves.workshopmongo.dto.UserDTO;
import com.jhonatasalves.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/workshop/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findAll() {
		List<Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{idPost}", method = RequestMethod.GET)
	public ResponseEntity<Post> findPosts(@PathVariable String idPost) {
		Post post = service.findById(idPost);
		return ResponseEntity.ok().body(post);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Post objPost) {
		service.postInsert(objPost);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objPost.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
