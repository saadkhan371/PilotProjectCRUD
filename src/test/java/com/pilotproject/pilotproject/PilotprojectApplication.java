package com.pilotproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pilotproject.exception.ResourceNotFoundException;
import com.pilotproject.model.Users;
import com.pilotproject.repo.UsersRepo;

import javax.validation.Valid;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@SpringBootApplication
@RequestMapping("/api")
public class PilotprojectApplication {

	private @Autowired UsersRepo users;

	public static void main(String[] args) {
		SpringApplication.run(PilotprojectApplication.class, args);
	}

	@PostMapping("/create")
	public Users Create(@Valid @RequestBody Users user) {
		user.setCreatedAt(new Date());
		return users.save(user);
	}

	@PostMapping("/update/{id}")
	public Users update(@PathVariable(value = "id") Long userId, @Valid @RequestBody Users user) {
		Users obj = users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "id", userId));
		obj.setName(user.getName());		
		obj.setUpdatedAt(new Date());
		
		Users updatedUsers = users.save(obj);
		return updatedUsers;
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {
		Users obj = users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "id", userId));
		users.delete(obj);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/list")
	public List<Users> List() {
		return users.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Users Get(@PathVariable(value = "id") Long userId) {
		Users obj = users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "id", userId));
		return obj;
	}
	

}
