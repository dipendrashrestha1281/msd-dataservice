package com.bah.msd.mcc.api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.msd.mcc.domain.Event;
import com.bah.msd.mcc.repository.EventRepository;

@RestController
@RequestMapping("/events")
public class EventApi {


	@Autowired
	EventRepository repo;
	
	@GetMapping
	public Iterable<Event> getAll() {
		return repo.findAll();
	}
	


	@GetMapping("/{eventId}")
	public Optional<Event> getEventById(@PathVariable("eventId") long id) {
		return repo.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<?> addEvent(@RequestBody Event newEvent, UriComponentsBuilder uri) {
		if (newEvent.getId() !=0
			|| newEvent.getTitle() == null
			|| newEvent.getCode() == null
			|| newEvent.getDescription() == null) {
			
			return ResponseEntity.badRequest().build();
		}
		newEvent = repo.save(newEvent);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newEvent.getId()).toUri();
		ResponseEntity<?> response = ResponseEntity.created(location).build();
		return response;
	}
	
	@PutMapping("/{eventId}")
	public ResponseEntity<?> putEvent(
			@RequestBody Event newEvent, 
			@PathVariable("eventId") long eventId) {
		if (newEvent.getId() != eventId 
			|| newEvent.getTitle() == null
			|| newEvent.getCode() == null
			|| newEvent.getDescription() == null) {
			
			return ResponseEntity.badRequest().build();
		}
		newEvent = repo.save(newEvent);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{eventId}")
	public ResponseEntity<?> deleteEventById(@PathVariable ("eventId") long id) {
		repo.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
}
	
//	// Dummy Data
//	ArrayList<Event> eventList = new ArrayList<Event>();
//
//	public EventApi() {
//		Event e1 = new Event(1, "CNF001", "All-Java Conference", "Lectures and exhibits covering all Java topics");
//		Event e2 = new Event(2, "WKS002", "Spring Boot Workshop", "Hands-on Spring Boot Workshop");
//		Event e3 = new Event(3, "TRN001", "Angular Training Course", "Five day introductory training in Angular");
//		Event e4 = new Event(4, "RNR004", "Rock n Roll Concert", "BAH Employees RocknRoll Concert");
//		
//		eventList.add(e1);
//		eventList.add(e2);
//		eventList.add(e3);
//		eventList.add(e4);
//		
//	}
//
//	// Get All
//	@GetMapping
//	public Collection<Event> getAll() {
//		return this.eventList;
//	}
//	
//	// Get One
//	@GetMapping("/{eventId}")
//	public Event getOneById(@PathVariable("eventId") long id) {
//		
//		// Set to null
//		Event event = null;
//		
//		// return if ID matches
//		for (int i = 0; i < eventList.size(); i++) {
//			if (eventList.get(i).getId() == id) {
//				event = eventList.get(i);
//			}
//		}
//		// If no match return event as null
//		return event;
//	}


/*	
	 * // Dummy Data ArrayList<Event> eventList = new ArrayList<Event>();
	 * 
	 * public EventApi() { Event e1 = new Event(1, "CNF001", "All-Java Conference",
	 * "Lectures and exhibits covering all Java topics"); Event e2 = new Event(2,
	 * "WKS002", "Spring Boot Workshop", "Hands-on Spring Boot Workshop"); Event e3
	 * = new Event(3, "TRN001", "Angular Training Course",
	 * "Five day introductory training in Angular"); Event e4 = new Event(4,
	 * "RNR004", "Rock n Roll Concert", "BAH Employees RocknRoll Concert");
	 * 
	 * eventList.add(e1); eventList.add(e2); eventList.add(e3); eventList.add(e4);
	 * 
	 * }
	 * 
	 * // Get All
	 * 
	 * @GetMapping public Collection<Event> getAll() { return this.eventList; }
	 * 
	 * // Get One
	 * 
	 * @GetMapping("/{eventId}") public Event getOneById(@PathVariable("eventId")
	 * long id) {
	 * 
	 * // Set to null Event event = null;
	 * 
	 * // return if ID matches for (int i = 0; i < eventList.size(); i++) { if
	 * (eventList.get(i).getId() == id) { event = eventList.get(i); } } // If no
	 * match return event as null return event; }
	 
}*/
//}

