package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.SaveCallback;

import java.util.ArrayList;

public class NewProjectActivity
		extends AppCompatActivity implements EditTextView.OnAddListener{

	private EditText mTitleET;
	private EditText mDescriptionET;


	private ArrayList<String> mMembers;
	private ArrayList<String> mNeeded;

	private LinearLayout mMembersLayout;
	private LinearLayout mNeededLayout;

	private Button mCreateBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_project);

		mMembers = new ArrayList<>();
		mNeeded = new ArrayList<>();

		mMembersLayout = (LinearLayout)findViewById(R.id.members_layout);
		mMembersLayout.addView(new EditTextView(this, this, EditTextView.TYPE_MEMBER));

		mNeededLayout = (LinearLayout)findViewById(R.id.needed_layout);
		mNeededLayout.addView(new EditTextView(this, this, EditTextView.TYPE_NEEDED));

		mTitleET = (EditText) findViewById(R.id.title_ET);
		mDescriptionET = (EditText) findViewById(R.id.description_ET);

		mCreateBtn = (Button)findViewById(R.id.create);
		mCreateBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String title = mTitleET.getText().toString();
				String description = mDescriptionET.getText().toString();
				if (title != null && description != null && !mMembers.isEmpty() && !mNeeded.isEmpty()) {
					createNewProject(title, description, mMembers, mNeeded);
				}
			}
		});

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

	@Override
	public void onAdded(String name, int type) {
		if (type == EditTextView.TYPE_MEMBER) {
			mMembers.add(name);
			mMembersLayout.addView(new EditTextView(this, this, type));
		} else if (type == EditTextView.TYPE_NEEDED) {
			mNeeded.add(name);
			mNeededLayout.addView(new EditTextView(this, this, type));
		}

	}
}
