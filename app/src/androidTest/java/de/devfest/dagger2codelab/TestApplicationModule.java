package de.devfest.dagger2codelab;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import de.devfest.dagger2codelab.data.api.GitHubApi;
import de.devfest.dagger2codelab.data.response.RepositoryResponse;
import de.devfest.dagger2codelab.data.response.UserResponse;
import de.devfest.dagger2codelab.di.modules.ApplicationModule;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by r.campos on 11/5/15.
 */
public class TestApplicationModule extends ApplicationModule {

	public TestApplicationModule(Context context) {
		super(context);
	}

	@Override
	protected GitHubApi provideGitHubApi() {
		return new GitHubApi() {
			@Override
			public Observable<List<UserResponse>> getUsers() {
				UserResponse userResponse = new UserResponse();
				userResponse.id = Long.MAX_VALUE;
				userResponse.login = "testLogin";
				userResponse.name = "Test Name";
				userResponse.url = "http://test.com";
				List<UserResponse> response = new ArrayList<>();
				response.add(userResponse);
				return Observable.just(response);
			}

			@Override
			public Observable<List<RepositoryResponse>> getUsersRepositories(@Path("username") String username) {
				return null;
			}
		};
	}
}
