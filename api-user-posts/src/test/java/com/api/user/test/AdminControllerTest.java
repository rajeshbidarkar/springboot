package com.api.user.test;

import static com.api.user.test.PostMatcher.isPost;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.api.controller.AdminController;
import com.api.domain.Post;
import com.api.domain.User;
import com.api.exceptionhandler.UserNotFoundException;
import com.api.placeholder.JsonPlaceholderService;

public class AdminControllerTest {

	private static final int USER_ID = 1;

	private AdminController controller;

	private JsonPlaceholderService jsonPlaceholderService;

	@Before
	public void before() {
		jsonPlaceholderService = mock(JsonPlaceholderService.class);
		controller = new AdminController(jsonPlaceholderService);
	}

	@Test
	public void testGet() throws Exception {
		User user = createUser(USER_ID, "MS Dhoni", "dhoni@gmail.com");
		List<User> users = new ArrayList<>();
		users.add(user);
		when(jsonPlaceholderService.getUsers()).thenReturn(users);

		List<Post> posts = new ArrayList<>();
		posts.add(createPost(USER_ID, 1, "Title 1"));
		posts.add(createPost(USER_ID, 2, "Title 2"));
		posts.add(createPost(USER_ID, 3, "Title 3"));

		when(jsonPlaceholderService.getPostsByUserId(USER_ID)).thenReturn(posts);

		List<User> users2 = controller.get();
		User result = users2.get(0);
		assertThat(result.getId(), is(1));
		assertThat(result.getName(), is("MS Dhoni"));
		assertThat(result.getEmail(), is("dhoni@gmail.com"));

		assertThat(result.getPosts(), hasSize(3));
		assertThat(result.getPosts().get(0), isPost(USER_ID, 1, "Title 1"));
		assertThat(result.getPosts().get(1), isPost(USER_ID, 2, "Title 2"));
		assertThat(result.getPosts().get(2), isPost(USER_ID, 3, "Title 3"));
	}

	@Test
	public void testGetButUserHasNoPosts() throws Exception {

		User user = createUser(USER_ID, "MS Dhoni", "dhoni@gmail.com");
		List<User> users = new ArrayList<>();
		users.add(user);
		when(jsonPlaceholderService.getUsers()).thenReturn(users);

		List<Post> posts = Collections.emptyList();
		when(jsonPlaceholderService.getPostsByUserId(USER_ID)).thenReturn(posts);

		List<User> results = controller.get();
		User result = results.get(0);
		assertThat(result.getId(), is(1));
		assertThat(result.getName(), is("MS Dhoni"));
		assertThat(result.getEmail(), is("dhoni@gmail.com"));
		assertThat(result.getPosts(), is(empty()));
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetButUserNotFound() throws Exception {
		List<User> users = new ArrayList<>();
		when(jsonPlaceholderService.getUsers()).thenReturn(users);
		controller.get();
	}

	private User createUser(int userId, String name, String email) {
		User user = new User();
		user.setId(userId);
		user.setName(name);
		user.setEmail(email);
		return user;
	}

	private Post createPost(int userId, int id, String title) {
		Post post = new Post();
		post.setUserId(userId);
		post.setId(id);
		post.setTitle(title);
		return post;
	}
}
