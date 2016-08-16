package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Author;
import com.example.repository.AuthorRepository;

@Service
public class AuthorService {

	private AuthorRepository authorRepository;
	
	@Autowired
	public AuthorService(AuthorRepository authorRepository) {
		super();
		this.authorRepository = authorRepository;
	}

	public List<Author> list() {
		return authorRepository.findAllByOrderByLastNameAscFirstNameAsc();
	}
	
	public Author get(Long id){
		return authorRepository.findOne(id);
	}
	
	public Author save(Author author){
		return authorRepository.save(author);
	}
	
	public void delete(Long id){
		authorRepository.delete(id);
	}
	
	

}
