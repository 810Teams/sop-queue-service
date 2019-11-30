package com.bank.springhibernate.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.bank.springhibernate.model.Comment;
import com.bank.springhibernate.repositories.CommentRepository;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller

public class CommentController {

	@Autowired
	private CommentRepository comment;

	public boolean checkToken(final ServletRequest req) throws ServletException, URISyntaxException {

		URI location = new URI("https://auth-service-258809.appspot.com/verify");
		final HttpServletRequest request = (HttpServletRequest) req;
		final String authHeader = request.getHeader("authorization");

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", authHeader);

			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<String> response = restTemplate.exchange(location, HttpMethod.GET, entity, String.class);
			if (response.getStatusCode().is2xxSuccessful()) {
				return true;
			}
		} catch (Exception eek) {
			throw new ServletException("Missing or invalid Authorization header");
		}
		return false;

	}

	@GetMapping("/comment")
	public List<Comment> retrieveAllStudents(final ServletRequest req) throws ServletException, URISyntaxException {
		if (checkToken(req)) {
			return comment.findAll();
		} else {
			throw new ServletException("Missing or invalid Authorization header");
		}
	}

	@GetMapping("/comment/{name}")
	public List<Comment> retrieveComment(@PathVariable String name, final ServletRequest req) throws ServletException, URISyntaxException {
		if (checkToken(req)) {
			return comment.findByName(name);
		} else {
			throw new ServletException("Missing or invalid Authorization header");
		}
		
	}

//	@DeleteMapping("/comment/{id}")
//	public void deleteComment(@PathVariable long id) {
//		comment.deleteById(id);
//	}

	@PostMapping("/comment")
	public ResponseEntity<Object> createComment(@RequestBody Comment review, final ServletRequest req)
			throws ServletException, URISyntaxException {
		if (checkToken(req)) {
			Comment savedComment = comment.save(review);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedComment.getId()).toUri();
			return ResponseEntity.created(location).build();
		} else {
			throw new ServletException("Missing or invalid Authorization header");
		}
		
	}

//	@PutMapping("/comment/{id}")
//	public ResponseEntity<Object> updateComment(@RequestBody Comment reviews, @PathVariable long id) {
//
//		Optional<Comment> CommentOptional = comment.findbyName(id);
//
//		if (!CommentOptional.isPresent())
//			return ResponseEntity.notFound().build();
//
//		reviews.setId(id);
//		
//		comment.save(reviews);
//
//		return ResponseEntity.noContent().build();
//	}
}