package de.devfest.dagger2codelab.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.devfest.dagger2codelab.R;
import de.devfest.dagger2codelab.data.api.GitHubApi;
import de.devfest.dagger2codelab.data.api.GitHubApiService;
import de.devfest.dagger2codelab.di.scopes.ApplicationScope;
import retrofit.RestAdapter;

@Module
public class ApplicationModule {

	private final Context context;

	public ApplicationModule(Context context) {
		this.context = context;
	}

	@ApplicationScope
	@Provides
	Context provideApplicationContext() {
		return context;
	}

	@ApplicationScope
	@Provides
	GitHubApi provideGitHubApi(){
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint(context.getString(R.string.endpoint))
				.build();
		return restAdapter.create(GitHubApi.class);
	}
}
