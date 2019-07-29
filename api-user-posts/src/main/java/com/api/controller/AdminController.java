package com.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.User;
import com.api.exceptionhandler.UserNotFoundException;
import com.api.placeholder.JsonPlaceholderService;

/**
 * Admin controller which will act as main controller call
 * 
 * @author
 *
 */
@RestController
public class AdminController {

	private JsonPlaceholderService jsonPlaceholderService;

	@Autowired
	public AdminController(JsonPlaceholderService jsonPlaceholderService) {
		this.jsonPlaceholderService = jsonPlaceholderService;
	}

	/**
	 * This method will return all users list which will have its related posts
	 * also.
	 * 
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws UserNotFoundException
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public List<User> get() throws IOException, InterruptedException, ExecutionException, UserNotFoundException {
		List<User> users = jsonPlaceholderService.getUsers();
		users.stream().filter(Objects::nonNull).forEach(user -> {
			try {
				user.setPosts(jsonPlaceholderService.getPostsByUserId(user.getId()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		return users;
	}
}
