package com.projectmanager.microservices.projectsservice.entities;

import java.util.ArrayList;
import java.util.List;

public class Projects {
	private List<Project> projects;
	private String port;

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void addProjects(Project project) {
		if (null == projects) {
			projects = new ArrayList<Project>();
		}
		projects.add(project);
	}
}
