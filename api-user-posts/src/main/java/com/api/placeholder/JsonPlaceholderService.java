package com.api.placeholder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.api.domain.Post;
import com.api.domain.User;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Service
public class JsonPlaceholderService {

	private final IPlaceHolderJson placeHolder;
	@Autowired
	public JsonPlaceholderService(@Value("${JsonPlaceholderService.baseUrl}") String baseUrl) {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(JacksonConverterFactory.create()).build();
		placeHolder = retrofit.create(IPlaceHolderJson.class);
	}

	@Async
	public Future<List<User>> getUsers() throws IOException {
		List<User> users = placeHolder.getUsers().execute().body();
		return new AsyncResult<>(users);
	}

	@Async
	public Future<List<Post>> getPostsByUserId(int userId) throws IOException {
		List<Post> posts = placeHolder.getPostsByUserId(userId).execute().body();
		return new AsyncResult<>(posts);
	}

}
