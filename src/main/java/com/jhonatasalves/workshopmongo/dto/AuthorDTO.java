package com.jhonatasalves.workshopmongo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jhonatasalves.workshopmongo.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	public AuthorDTO() {

	}

	public AuthorDTO(User obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

}
