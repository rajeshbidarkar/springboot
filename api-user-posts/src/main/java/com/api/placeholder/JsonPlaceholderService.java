package com.api.placeholder;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.api.domain.Post;
import com.api.domain.User;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Service class for controller to fetch users and its posts
 * 
 * @author
 *
 */
@Service
public class JsonPlaceholderService {

	private final IPlaceHolderJson placeHolder;

	@Autowired
	public JsonPlaceholderService(@Value("${JsonPlaceholderService.baseUrl}") String baseUrl) {
		// Created converter factory for serialization and de-serialization
		Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(JacksonConverterFactory.create()).build();
		// Implementation of the API end points for place holder
		placeHolder = retrofit.create(IPlaceHolderJson.class);
	}

	/**
	 * This method returns All Users list
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<User> getUsers() throws IOException {
		// Execute the request and return the response
		return placeHolder.getUsers().execute().body();
	}

	/**
	 * This method returns user posts by userId
	 * 
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public List<Post> getPostsByUserId(int userId) throws IOException {
		return placeHolder.getPostsByUserId(userId).execute().body();
	}
}
