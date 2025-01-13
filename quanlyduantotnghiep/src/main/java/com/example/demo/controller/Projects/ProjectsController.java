package com.example.demo.controller.Projects;

import com.example.demo.entity.ProjectMembers.ProjectMembers;
import com.example.demo.entity.Projects.Projects;
import com.example.demo.service.ProjectMembers.ProjectMembersService;
import com.example.demo.service.Projects.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    public ProjectsService projectsService;

    @Autowired
    public ProjectMembersService projectMembersService;

    @GetMapping("")
    public String getFaculties(Model model) {
        return "projects/index";
    }

	@GetMapping("/details")
	public String projectDetails(Model model) {
		return "projects/details";
	}

    @GetMapping("/all")
    public ResponseEntity<List<Projects>> getProjects() {
        List<Projects> projects = this.projectsService.getProjects();
        return ResponseEntity.ok(projects);
    }

    //lấy thông tin đề tài theo id
	@GetMapping("/{prId}")
	public ResponseEntity<Object> getProjects(@PathVariable int prId) {
		Projects projects = this.projectsService.getProjects(prId);

		if (projects == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		// Fetch project members
		List<ProjectMembers> members = this.projectMembersService.getProjectMembers();

		// Create a combined response object
		Map<String, Object> response = new HashMap<>();
		response.put("project", projects);
		response.put("members", members);

		return ResponseEntity.ok(response);
	}


	//thêm mới project
    @PostMapping("/create")
    public ResponseEntity<Projects> addProjects(@RequestBody Projects pr) {
        Projects projects = this.projectsService.addProjects(pr);
        System.out.println("Start Date: " + projects.getStartday());
        return ResponseEntity.status(HttpStatus.CREATED).body(projects);
    }

    //Cập nhật thông tin projects
    @PutMapping("/update/{prId}")
    public ResponseEntity<Projects> updateProjects(@PathVariable long prId, @RequestBody Projects pr) {
        Projects updateProjects = this.projectsService.updateProjects(prId, pr);
        if (updateProjects == null) {
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
