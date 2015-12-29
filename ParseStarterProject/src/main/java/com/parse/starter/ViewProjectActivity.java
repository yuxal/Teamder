package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ViewProjectActivity extends AppCompatActivity {

	public static String EXTRA_PROJECT = "project";

	private Project mProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

	    mProject = (Project) getIntent().getSerializableExtra(EXTRA_PROJECT);

	    ((TextView)findViewById(R.id.textViewTitle)).setText(mProject.getTitle());
	    ((TextView)findViewById(R.id.textViewDescription)).setText(mProject.getDescription());
	    ((ListView) findViewById(R.id.listViewMembers)).setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mProject.getMembers()));
	    ((ListView) findViewById(R.id.listViewNeededJobs)).setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, mProject.getNeeded()));
    }
}
