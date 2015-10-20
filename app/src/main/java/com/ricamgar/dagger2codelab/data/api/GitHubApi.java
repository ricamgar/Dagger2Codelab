package com.ricamgar.dagger2codelab.data.api;

import com.ricamgar.dagger2codelab.data.response.RepositoryResponse;
import com.ricamgar.dagger2codelab.data.response.UserResponse;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Interface to provide GitHub Api methods
 */
public interface GitHubApi {

	@GET("/users")
	Observable<List<UserResponse>> getUsers();

	@GET("/users/{username}/repos")
	Observable<List<RepositoryResponse>> getUsersRepositories(
			@Path("username") String username
	);
}
