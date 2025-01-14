package com.example.demo.service.ProjectMembers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProjectMembers.ProjectMembers;
import com.example.demo.repository.ProjectMembers.ProjectMembersRepository;

@Service
public class ProjectMembersServiceImpl implements ProjectMembersService{

	@Autowired
	private ProjectMembersRepository projectMembersRepository;

	@Override
	public List<ProjectMembers> getProjectMembers() {
		return projectMembersRepository.findAll();	}

	@Override
	public ProjectMembers addProjectMembers(ProjectMembers prb) {
		projectMembersRepository.save(prb);
		return prb;
	}

	@Override
	public ProjectMembers getProjectMembers(long prbId) {
		ProjectMembers prb = projectMembersRepository.findById(prbId).get();
		return prb;
	}

	@Override
	public List<ProjectMembers> getProjectMembersByProjectId(long prbId) {
        return projectMembersRepository.findByProject_Id(prbId);
	}

	@Override
	public ProjectMembers updateProjectMembers(long prbId, ProjectMembers prb) {
		ProjectMembers exit = projectMembersRepository.findById(prb.getId()).orElse(null);
		
		if(exit == null) {
			throw new RuntimeException("ProjectMembers not found with id: " + prbId);
		}
		
		if(prb.getProject() != null) {
			exit.setProject(prb.getProject());
		}
		exit.setMasinhvien(prb.getMasinhvien());
		exit.setName(prb.getName());
		exit.setRole(prb.getRole());
		return projectMembersRepository.save(exit);
	}

	@Override
	public ProjectMembers deleteProjectMembers(long id) {
		projectMembersRepository.delete(projectMembersRepository.findById(id).get());
		return null;
	}

	
	
}
