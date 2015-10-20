package com.ricamgar.dagger2codelab.data.api;

import android.content.Context;

import com.ricamgar.dagger2codelab.R;

import retrofit.RestAdapter;

/**
 * Service to provide the GitHub Api service
 */
public class GitHubApiService {

	private static GitHubApi service;

	private GitHubApiService() {
	}

	public static GitHubApi get(Context context) {
		if (service == null) {
			RestAdapter restAdapter = new RestAdapter.Builder()
					.setEndpoint(context.getString(R.string.endpoint))
					.build();
			service = restAdapter.create(GitHubApi.class);
		}
		return service;
	}
}
