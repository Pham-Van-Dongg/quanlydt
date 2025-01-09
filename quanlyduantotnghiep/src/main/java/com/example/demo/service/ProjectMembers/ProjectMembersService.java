package com.example.demo.service.ProjectMembers;
import java.util.List;
import com.example.demo.entity.ProjectMembers.ProjectMembers;
public interface ProjectMembersService {

	public List<ProjectMembers> getProjectMembers();
	
	public ProjectMembers addProjectMembers(ProjectMembers prb);
	
	public ProjectMembers getProjectMembers(long prbId);
	
	public ProjectMembers updateProjectMembers(long prbId, ProjectMembers prb);
	
	public ProjectMembers deleteProjectMembers(long id);
}
