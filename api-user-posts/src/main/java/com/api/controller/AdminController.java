package com.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.User;
import com.api.exceptionhandler.UserNotFoundException;
import com.api.placeholder.JsonPlaceholderService;

@RestController
public class AdminController {

	private JsonPlaceholderService jsonPlaceholderService;

	@Autowired
	public AdminController(JsonPlaceholderService jsonPlaceholderService) {		
		this.jsonPlaceholderService = jsonPlaceholderService;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public List<User> get() throws IOException, InterruptedException, ExecutionException, UserNotFoundException {
		Future<List<User>> futureUser = jsonPlaceholderService.getUsers();
		List<User> users = futureUser.get();
		users.stream().filter(Objects::nonNull).forEach(user -> {
			try {
				user.setPosts(jsonPlaceholderService.getPostsByUserId(user.getId()).get());
			} catch (InterruptedException | ExecutionException | IOException e) {				
				e.printStackTrace();
			}
		});
		return users;
	}
}
