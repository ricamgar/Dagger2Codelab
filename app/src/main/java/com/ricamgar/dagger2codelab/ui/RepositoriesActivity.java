package com.ricamgar.dagger2codelab.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ricamgar.dagger2codelab.R;
import com.ricamgar.dagger2codelab.data.api.GitHubApi;
import com.ricamgar.dagger2codelab.data.api.GitHubApiService;
import com.ricamgar.dagger2codelab.data.response.RepositoryResponse;
import com.ricamgar.dagger2codelab.ui.adapter.ReposListAdapter;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Activity to show the list of repositories
 */
public class RepositoriesActivity extends AppCompatActivity {

	private static final String EXTRA_USER_LOGIN = "com.ricamgar.dagger2codelab.USER_LOGIN";

	private GitHubApi service;

	public static Intent createIntent(Context context, String login) {
		Intent intent = new Intent(context, RepositoriesActivity.class);
		intent.putExtra(EXTRA_USER_LOGIN, login);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

		service = GitHubApiService.get(this);
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
