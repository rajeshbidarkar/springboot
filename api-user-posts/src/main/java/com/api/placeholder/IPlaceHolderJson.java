package com.api.placeholder;

import java.util.List;

import com.api.domain.Post;
import com.api.domain.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPlaceHolderJson {

	@GET("/users")
	Call<List<User>> getUsers();

	@GET("/posts")
	Call<List<Post>> getPostsByUserId(@Query("userId") int userId);
}
