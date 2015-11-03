package de.devfest.dagger2codelab.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import de.devfest.dagger2codelab.R;
import de.devfest.dagger2codelab.data.response.UserResponse;
import de.devfest.dagger2codelab.ui.adapter.UsersListAdapter.UserViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter to display users list
 */
public class UsersListAdapter extends RecyclerView.Adapter<UserViewHolder>{

	private UserListListener listener;
	private List<UserResponse> items = new ArrayList<>();

	public UsersListAdapter(UserListListener listener) {
		this.listener = listener;
	}

	public void addItems(List<UserResponse> items) {
		this.items = items;
		notifyDataSetChanged();
	}

	@Override
	public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false);
		return new UserViewHolder(view);
	}

	@Override
	public void onBindViewHolder(UserViewHolder holder, int position) {
		final UserResponse userResponse = items.get(position);
		holder.text1.setText(userResponse.login);
		holder.text2.setText(userResponse.url);
		holder.itemView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null) listener.onUserClicked(userResponse.login);
			}
		});
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	static class UserViewHolder extends RecyclerView.ViewHolder{

		public final TextView text1;
		public final TextView text2;

		public UserViewHolder(View itemView) {
			super(itemView);
			text1 = (TextView) itemView.findViewById(R.id.text1);
			text2 = (TextView) itemView.findViewById(R.id.text2);
		}
	}

	public interface UserListListener {
		void onUserClicked(String login);
	}
}
