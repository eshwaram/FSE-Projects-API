package com.projectmanager.microservices.projectsservice.controllers;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.microservices.projectsservice.entities.Project;
import com.projectmanager.microservices.projectsservice.entities.Projects;
import com.projectmanager.microservices.projectsservice.repositories.ProjectRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProjectsController {

	@Autowired
	ProjectRepository repo;
	
	@Autowired
	Environment env;

	@PostMapping("/addproject")
	public void addUser(@RequestBody Project project) {
		repo.save(project);
	}
	
	@DeleteMapping("/deleteproject/{pid}")
	public void deleteUser(@PathVariable long pid) {
		repo.deleteById(pid);
	}

	@GetMapping("/getprojects")
	public Projects retrieveProjects() {
		Set<Project> dbData = repo.findAll();
		Projects result = new Projects();
		result.setPort(env.getProperty("local.server.port"));

		for (Project data : dbData) {
			Project project = new Project();
			BeanUtils.copyProperties(data, project);
			result.addProjects(project);
		}
		return result;
	}
	
	@PutMapping("/updateproject/{pid}")
	public void updateUser(@RequestBody Project project, @PathVariable long pid) {
		Optional<Project> dbData = repo.findById(pid);
		Project existProj = new Project();
		if (dbData.isPresent()) {
			existProj = dbData.get();
		}
		existProj.setProjName(project.getProjName());
		existProj.setStartDate(project.getStartDate());
		existProj.setEndDate(project.getEndDate());
		existProj.setPriority(project.getPriority());
		existProj.setUserID(project.getUserID());
		repo.save(existProj);
	}
	
	@GetMapping("/getproject/{pid}")
	public Project getTask(@PathVariable long pid) {
		Optional<Project> dbData = repo.findById(pid);
		Project existProject = new Project();
		if (dbData.isPresent()) {
			existProject = dbData.get();
		}
		return existProject;
	}

}
