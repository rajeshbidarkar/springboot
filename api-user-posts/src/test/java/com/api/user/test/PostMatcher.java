package com.api.user.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.api.domain.Post;

public class PostMatcher extends TypeSafeMatcher<Post> {

	private final String title;

	private final int userId;

	private final int id;

	public static PostMatcher isPost(int userId, int id, String title) {
		return new PostMatcher(userId, id, title);
	}

	private PostMatcher(int userId, int id, String title) {
		this.userId = userId;
		this.id = id;
		this.title = title;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("Must match: ") //
				.appendText("title=") //
				.appendText(title) //
				.appendText(", userId=") //
				.appendText(userId + "") //
				.appendText(", id=") //
				.appendText(id + ""); //
	}

	@Override
	protected boolean matchesSafely(Post post) {
		return title.equals(post.getTitle()) && userId == post.getUserId() && id == post.getId();
	}

}
