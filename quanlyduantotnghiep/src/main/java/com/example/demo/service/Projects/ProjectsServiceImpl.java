package com.example.demo.service.Projects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Projects.Projects;
import com.example.demo.repository.Projects.ProjectsRepository;

@Service
public class ProjectsServiceImpl implements ProjectsService {
	
	@Autowired
	private ProjectsRepository projectsRepository;

	@Override
	public List<Projects> getProjects() {

		return projectsRepository.findAll();
	}

	@Override
	public Projects addProjects(Projects pr) {
		projectsRepository.save(pr);
		return pr;
	}

	@Override
	public Projects getProjects(long prId) {
		Projects pr = projectsRepository.findById(prId).get();
		return pr;
	}

	@Override
	public Projects updateProjects(Projects pr) {
		Projects existingProjects = projectsRepository.findById(pr.getId()).orElse(null);
		
		if(existingProjects == null) {
			throw new RuntimeException("Projects not found with Id: " + pr.getId());
		}
		
		existingProjects.setName(pr.getName());
		existingProjects.setDescription(pr.getDescription());
		existingProjects.setStartday(pr.getStartday());
		existingProjects.setEndday(pr.getEndday());
		existingProjects.setStatus(pr.getStatus());
		// Cập nhật faculty nếu có
	    if (pr.getFaculty() != null) {
	        existingProjects.setFaculty(pr.getFaculty()); // Gán đối tượng Faculty cho trường faculty của Projects
	    }
		return projectsRepository.save(existingProjects);
	}

	@Override
	public Projects deleteProjects(long id) {
		projectsRepository.delete(projectsRepository.findById(id).get());
		return null;
	}
	
	
}