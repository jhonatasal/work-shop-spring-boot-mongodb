package com.jhonatasalves.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhonatasalves.workshopmongo.domain.User;
import com.jhonatasalves.workshopmongo.dto.UserDTO;
import com.jhonatasalves.workshopmongo.repository.UserRepository;
import com.jhonatasalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User userInsert(User obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public User update(User newObj) {
		User obj = findById(newObj.getId());
		updateData(obj, newObj);
		return repo.save(obj);
	}

	private void updateData(User obj, User newObj) {
		obj.setName(newObj.getName());
		obj.setEmail(newObj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}

}
