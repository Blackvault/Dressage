package com.blackvault;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class UserRestController {
	@Autowired
	private UserRepository reposity;

	UserRestController(UserRepository repository) {
		this.reposity = repository;
	}

	@GetMapping("/user")
	CollectionModel<EntityModel<User>> all() {

		List<EntityModel<User>> users = reposity.findAll().stream()
				.map(user -> EntityModel.of(user,
						linkTo(methodOn(UserRestController.class).one(user.getId())).withSelfRel(),
						linkTo(methodOn(UserRestController.class).all()).withRel("users")))
				.collect(Collectors.toList());

		return CollectionModel.of(users, linkTo(methodOn(UserRestController.class).all()).withSelfRel());

	}

	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable Long id) {
		reposity.deleteById(id);
		return "User Deleted";
	}

	@GetMapping("/user/{id}")
	EntityModel<User> one(@PathVariable Long id) {

		User user = reposity.findById(id) //
				.orElseThrow(() -> new UserNotFoundException(id));

		return EntityModel.of(user, //
				linkTo(methodOn(UserRestController.class).one(id)).withSelfRel(),
				linkTo(methodOn(UserRestController.class).all()).withRel("users"));
	}
}
