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
public class RiderController {
	@Autowired
	private RiderRepository reposity;

	RiderController(RiderRepository repository) {
		this.reposity = repository;
	}

	@GetMapping("/riders")
	CollectionModel<EntityModel<Rider>> all() {

		List<EntityModel<Rider>> riders = reposity.findAll().stream()
				.map(rider -> EntityModel.of(rider,
						linkTo(methodOn(RiderController.class).one(rider.getId())).withSelfRel(),
						linkTo(methodOn(RiderController.class).all()).withRel("riders")))
				.collect(Collectors.toList());

		return CollectionModel.of(riders, linkTo(methodOn(RiderController.class).all()).withSelfRel());

	}

	@DeleteMapping("/riders/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		reposity.deleteById(id);
		return "User Deleted";
	}

	@GetMapping("/riders/{id}")
	EntityModel<Rider> one(@PathVariable Long id) {

		Rider rider = reposity.findById(id) //
				.orElseThrow(() -> new RiderNotFoundException(id));

		return EntityModel.of(rider, //
				linkTo(methodOn(RiderController.class).one(id)).withSelfRel(),
				linkTo(methodOn(RiderController.class).all()).withRel("riders"));
	}
}
