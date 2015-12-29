package com.parse.starter;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

@ParseClassName("Project")
public class Project extends ParseObject implements Serializable{
	public Project() {
	}

	public Project(String title, String description) {
		setTitle(title);
		setDescription(description);
	}

	public String getTitle() {
		return getString("title");
	}

	public void setTitle(String title) {
		put("title", title);
	}
	public void setDescription(String description) {
		put("description", description);
	}

	public String getDescription() {
		return getString("description");
	}

	public ArrayList<String> getMembers() {
		JSONArray jsonArray = getJSONArray("members");
		ArrayList<String> members = new ArrayList<>();
		for (int i = 0 ; i < jsonArray.length() ; i++) {
			try {
				members.add(jsonArray.getString(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		put("members", members);
	}
	public ArrayList<String> getNeeded() {
		JSONArray jsonArray = getJSONArray("needed");
		ArrayList<String> needed = new ArrayList<>();

		for (int i = 0 ; i < jsonArray.length() ; i++) {
			try {
				needed.add(jsonArray.getString(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return needed;
	}
	public void setNeeded(ArrayList<String> needed) {
		put("needed", needed);
	}
	public static ParseQuery<Project> getQuery() {
		return ParseQuery.getQuery(Project.class);
	}
}
