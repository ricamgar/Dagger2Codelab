package com.ricamgar.dagger2codelab.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ricamgar.dagger2codelab.R;
import com.ricamgar.dagger2codelab.data.response.RepositoryResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to display user repositories
 */
public class ReposListAdapter extends RecyclerView.Adapter<ReposListAdapter.RepoViewHolder>{

	private List<RepositoryResponse> items = new ArrayList<>();

	public void addItems(List<RepositoryResponse> items) {
		this.items = items;
		notifyDataSetChanged();
	}

	@Override
	public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false);
		return new RepoViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RepoViewHolder holder, int position) {
		RepositoryResponse repoResponse = items.get(position);
		holder.text1.setText(repoResponse.name);
		holder.text2.setText(repoResponse.description);
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	static class RepoViewHolder extends RecyclerView.ViewHolder{

		public final TextView text1;
		public final TextView text2;

		public RepoViewHolder(View itemView) {
			super(itemView);
			text1 = (TextView) itemView.findViewById(R.id.text1);
			text2 = (TextView) itemView.findViewById(R.id.text2);
		}
	}
}
