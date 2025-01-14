package com.example.demo.controller.ProjectMembers;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.ProjectMembers.*;
import com.example.demo.service.ProjectMembers.*;

@RestController
@RequestMapping("/projectmembers")
public class ProjectMembersController {

	@Autowired
	public ProjectMembersService projectMembersService;
	
	@GetMapping("")
	public ResponseEntity<Map<String, List<Map<String, Object>>>> getGroupedProjectMembers() {
	    List<ProjectMembers> members = this.projectMembersService.getProjectMembers();
	    // Group by project name and map required fields
	    Map<String, List<Map<String, Object>>> groupedMembers = members.stream()
	        .collect(Collectors.groupingBy(
	            member -> member.getProject().getName(),
	            Collectors.mapping(member -> Map.of(
	                "masinhvien", member.getMasinhvien(),
	                "name", member.getName(),
	                "role", member.getRole()
	            ), Collectors.toList())
	        ));

	    return ResponseEntity.ok(groupedMembers);
	}
	
	//lay thong tin theo id
	@GetMapping("/{prbId}")
	public ResponseEntity<List<ProjectMembers>> getProjectMembers(@PathVariable long prbId) {
		List<ProjectMembers> projectMembers = this.projectMembersService.getProjectMembersByProjectId(prbId);
		if(projectMembers == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(projectMembers);
	}
	
	//them moi projectmember
	@PostMapping("/create")
	public ResponseEntity<ProjectMembers> addProjectMembers(@RequestBody ProjectMembers prb) {
		ProjectMembers pb = this.projectMembersService.addProjectMembers(prb);
		return ResponseEntity.status(HttpStatus.CREATED).body(pb);
	}
	
	//cap nhat thong tin projectmembers
	@PutMapping("/update/{prbId}")
	public ResponseEntity<ProjectMembers> updateProjectMembers(@PathVariable long prbId, @RequestBody ProjectMembers prb) {
		ProjectMembers up = this.projectMembersService.updateProjectMembers(prbId, prb);
		if(up == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(up);
	}
	
	//Xoa projectmembers
	@DeleteMapping("/delete/{prbId}")
	public ResponseEntity<Void> deleteProjectMembers(@PathVariable long prbId) {
		try {
			this.projectMembersService.deleteProjectMembers(prbId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}