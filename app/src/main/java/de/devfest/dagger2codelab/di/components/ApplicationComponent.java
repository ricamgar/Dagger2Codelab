package de.devfest.dagger2codelab.di.components;

import android.content.Context;

import dagger.Component;
import de.devfest.dagger2codelab.data.api.GitHubApi;
import de.devfest.dagger2codelab.di.modules.ApplicationModule;
import de.devfest.dagger2codelab.di.scopes.ApplicationScope;

@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

	Context getApplicationContext();

	GitHubApi getGitHubApi();
}
