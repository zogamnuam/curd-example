package com.example.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Author {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@Size(max = 50)
    @Autowired
    @Column(name = "first_name", length = 50)
	private String firstName;
	
	
	@Size(max = 50)
	@Column(name = "last_name", length = 50)
	@NotEmpty
	private String lastName;
	
	
	@NotEmpty
	private String email;
	
	//posts
	@OneToMany(mappedBy = "author",cascade = CascadeType.REMOVE, orphanRemoval = true )
	private List<Post> posts;
	

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@SuppressWarnings("unused")
	public Author(){}
	
	public Author(String first, String last){
		this.setFirstName(first);
		this.setLastName(last);
	}
	
	public Author(String first, String last, String email){
		this.setFirstName(first);
		this.setLastName(last);
		this.setEmail(email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Author [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}