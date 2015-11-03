package de.devfest.dagger2codelab.di.components;

import android.content.Context;

import dagger.Component;
import de.devfest.dagger2codelab.data.api.GitHubApi;

@Component
public interface ApplicationComponent {

	Context getApplicationContext();

	GitHubApi getGitHubApi();
}
