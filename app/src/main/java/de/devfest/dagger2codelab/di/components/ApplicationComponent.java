package de.devfest.dagger2codelab.di.components;

import android.content.Context;

import dagger.Component;
import de.devfest.dagger2codelab.data.api.GitHubApi;
import de.devfest.dagger2codelab.di.modules.ApplicationModule;
import de.devfest.dagger2codelab.di.scopes.ApplicationScope;
import de.devfest.dagger2codelab.ui.MainActivity;
import de.devfest.dagger2codelab.ui.RepositoriesActivity;

@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

	void inject(MainActivity activity);
	void inject(RepositoriesActivity activity);

	Context getApplicationContext();

	GitHubApi getGitHubApi();

	/**
	 * Helper to initialize the {@link ApplicationComponent}
	 */
	final class Initializer {

		private Initializer(){}

		public static ApplicationComponent init(ApplicationModule applicationModule) {
			return DaggerApplicationComponent.builder()
					.applicationModule(applicationModule)
					.build();
		}
	}

}
