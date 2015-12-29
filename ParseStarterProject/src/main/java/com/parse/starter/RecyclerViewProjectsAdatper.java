package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.starter.Project;
import com.parse.starter.R;
import com.parse.starter.ViewProjectActivity;

import java.util.List;

public class RecyclerViewProjectsAdatper extends RecyclerView.Adapter<RecyclerViewProjectsAdatper.ViewHolder>{
	private List<Project> mDataset;

	public class ViewHolder extends RecyclerView.ViewHolder {

		// Provide a reference to the views for each data item
		// Complex data items may need more than one view per item, and
		// you provide access to all the views for a data item in a view holder
		public Project  mProject;
		public TextView titleTextView;
		public TextView descriptionTextView;

		public ViewHolder(View v) {
			super(v);
			v.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Context context = v.getContext();
					Intent viewProjectIntent = new Intent(context, ViewProjectActivity.class);
					viewProjectIntent.putExtra(ViewProjectActivity.EXTRA_PROJECT, mProject);
					context.startActivity(viewProjectIntent);
				}
			});
			titleTextView = (TextView) v.findViewById(R.id.textViewTitle);
			descriptionTextView = (TextView) v.findViewById(R.id.textViewDescription);
		}

	}
	// Provide a suitable constructor (depends on the kind of dataset)
	public RecyclerViewProjectsAdatper(List<Project> myDataset) {
		mDataset = myDataset;
	}

	// Create new views (invoked by the layout manager)
	@Override
	public RecyclerViewProjectsAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// create a new view
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.recycler_item_project, parent, false);
		return new RecyclerViewProjectsAdatper.ViewHolder(v);
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(RecyclerViewProjectsAdatper.ViewHolder holder, int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element
		holder.mProject = mDataset.get(position);
		holder.titleTextView.setText(mDataset.get(position).getTitle());
		holder.descriptionTextView.setText(mDataset.get(position).getDescription());
	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return mDataset.size();
	}
}
