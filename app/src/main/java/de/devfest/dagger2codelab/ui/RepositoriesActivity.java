package de.devfest.dagger2codelab.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import de.devfest.dagger2codelab.DaggerApp;
import de.devfest.dagger2codelab.R;
import de.devfest.dagger2codelab.data.api.GitHubApi;
import de.devfest.dagger2codelab.data.response.RepositoryResponse;
import de.devfest.dagger2codelab.ui.adapter.ReposListAdapter;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Activity to show the list of repositories
 */
public class RepositoriesActivity extends AppCompatActivity {

	private static final String EXTRA_USER_LOGIN = "de.devfest.dagger2codelab.USER_LOGIN";

	@Inject
	GitHubApi service;

	public static Intent createIntent(Context context, String login) {
		Intent intent = new Intent(context, RepositoriesActivity.class);
		intent.putExtra(EXTRA_USER_LOGIN, login);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		((DaggerApp)getApplication()).getApplicationComponent().inject(this);

		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		RecyclerView reposListView = (RecyclerView) findViewById(R.id.list);
		reposListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		final ReposListAdapter adapter = new ReposListAdapter();
		reposListView.setAdapter(adapter);

		service.getUsersRepositories(getIntent().getStringExtra(EXTRA_USER_LOGIN))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Action1<List<RepositoryResponse>>() {
					@Override
					public void call(List<RepositoryResponse> repositoryResponses) {
						adapter.addItems(repositoryResponses);
					}
				});

	}
}
