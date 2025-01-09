package com.example.demo.controller.Projects;
import java.util.List;

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
import com.example.demo.entity.Projects.*;
import com.example.demo.service.Projects.ProjectsService;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
	
	@Autowired
	public ProjectsService projectsService;
	
	@GetMapping("")
	public ResponseEntity<List<Projects>> getProjects() {
		List<Projects> projects = this.projectsService.getProjects();
		return ResponseEntity.ok(projects);
	}
	
	//lấy thông tin đề tài theo id
	@GetMapping("/{prId}")
	public ResponseEntity<Projects> getProjects(@PathVariable long prId) {
		Projects projects = this.projectsService.getProjects(prId);
		if(projects == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(projects);
	}
	
	//thêm mới project
	@PostMapping("/created")
	public ResponseEntity<Projects> addProjects(@RequestBody Projects pr) {
		Projects projects = this.projectsService.addProjects(pr);
		System.out.println("Start Date: " + projects.getStartday());
		return ResponseEntity.status(HttpStatus.CREATED).body(projects);
	}
	
	//Cập nhật thông tin projects
	@PutMapping("/update/{prId}")
	public ResponseEntity<Projects> updateProjects(@PathVariable long prId, @RequestBody Projects pr) {
		Projects updateProjects = this.projectsService.updateProjects(prId, pr);
		if(updateProjects == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(updateProjects);
	}
	
	//Xóa project
	@DeleteMapping("/delete/{prId}")
	public ResponseEntity<Void> deleteProjects(@PathVariable long prId) {
		try {
			this.projectsService.deleteProjects(prId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
