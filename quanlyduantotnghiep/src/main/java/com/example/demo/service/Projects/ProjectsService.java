package com.example.demo.service.Projects;
import java.util.List;
import com.example.demo.entity.Projects.Projects;

public interface ProjectsService {
	
	public List<Projects> getProjects();
	
	public Projects addProjects(Projects pr);
	
	public Projects getProjects(long prId);
	
	public Projects updateProjects(long prId, Projects pr);
	
	public Projects deleteProjects(long id);
}
