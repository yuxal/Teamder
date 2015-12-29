package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class ViewProjectActivity extends AppCompatActivity {

	public static String EXTRA_PROJECT = "project";

	private String mProjectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

	    mProjectId = getIntent().getStringExtra(EXTRA_PROJECT);

		ParseQuery<Project> query = new ParseQuery<Project>(Project.class);
		query.getInBackground(mProjectId, new GetCallback<Project>() {
			@Override
			public void done(Project project, ParseException e) {
				((TextView) findViewById(R.id.textViewTitle)).setText(project.getTitle());
				((TextView) findViewById(R.id.textViewDescription)).setText(project.getDescription());
				((ListView) findViewById(R.id.listViewMembers)).setAdapter(new ArrayAdapter(ViewProjectActivity.this, android.R.layout.simple_list_item_1, project.getMembers()));
				((ListView) findViewById(R.id.listViewNeededJobs)).setAdapter(new ArrayAdapter(ViewProjectActivity.this, android.R.layout.simple_list_item_1, project.getNeeded()));
			}
		});




    }
}
