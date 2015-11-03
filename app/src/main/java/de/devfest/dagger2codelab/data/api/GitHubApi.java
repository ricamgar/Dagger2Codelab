package de.devfest.dagger2codelab.data.api;

import de.devfest.dagger2codelab.data.response.RepositoryResponse;
import de.devfest.dagger2codelab.data.response.UserResponse;

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
