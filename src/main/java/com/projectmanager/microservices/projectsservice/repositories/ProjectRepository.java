package com.projectmanager.microservices.projectsservice.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.projectmanager.microservices.projectsservice.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{
	Set<Project> findAll();
}
