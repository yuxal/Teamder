package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class NewProjectActivity
		extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_project);


	}

	private void createNewProject(String title, String description, ArrayList<String> members, ArrayList<String> needed) {
		// 1
		Project project = new Project();
		project.put("title", title);
		project.put("description", description);
		project.put("members", members);
		project.put("needed", needed);
		// 2
		ParseACL acl = new ParseACL();
		acl.setPublicReadAccess(true);
		project.setACL(acl);

		// 3
		project.saveInBackground(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				finish();
			}
		});
	}
}
