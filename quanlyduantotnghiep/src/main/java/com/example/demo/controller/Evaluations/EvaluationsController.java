package com.example.demo.controller.Evaluations;

import com.example.demo.entity.Evaluations.Evaluations;
import com.example.demo.entity.Projects.Projects;
import com.example.demo.repository.Projects.ProjectsRepository;
import com.example.demo.service.Evaluations.EvaluationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/evaluations")
public class EvaluationsController {

    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private EvaluationsService evaluationsService;

    public EvaluationsController(EvaluationsService evaluationsService) {
        this.evaluationsService = evaluationsService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<Evaluations>> getEvaluations() {
        List<Evaluations> evaluations = this.evaluationsService.getEvaluations();
        return ResponseEntity.ok(evaluations);
    }

    @GetMapping("/create")
    public String createEvaluations(Model model) {
        return "evaluations/create";
    }

    @GetMapping("")
    public String getFaculties(Model model) {
        return "evaluations/form_evaluations";
    }

    @GetMapping("/edit")
    public String editFaculty(Model model) {
        return "evaluations/edit";
    }

    // Lấy thông tin ev theo ID
    @GetMapping("/{evId}")
    public ResponseEntity<Evaluations> getEvaluations(@PathVariable long evId) {
        Evaluations evaluations = this.evaluationsService.getEvaluations(evId);
        if (evaluations == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(evaluations);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addEvaluations(@RequestBody Evaluations evaluations) {
        // Kiểm tra xem dự án có tồn tại không
        Projects project = projectsRepository.findById(evaluations.getProject().getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        // Gán đối tượng project vào documents
        evaluations.setProject(project);

        // Lưu đối tượng documents vào cơ sở dữ liệu
        evaluationsService.addEvaluations(evaluations);

        return ResponseEntity.ok("Evaluations saved successfully");
    }


    // Cập nhật thông tin khoa
    @PutMapping("/{evId}")
    public ResponseEntity<String> updateEvaluations(@PathVariable long evId, @RequestBody Evaluations ev) {
        Evaluations evaluations = this.evaluationsService.updateEvaluations(evId, ev);
        if (evaluations == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evaluation not found.");
        }
        return ResponseEntity.ok("Evaluation updated successfully.");
    }


    // Xóa de tai
    @DeleteMapping("/{evId}")
    public ResponseEntity<String> deleteEvaluations(@PathVariable long evId) {
        try {
            this.evaluationsService.deleteEvaluations(evId);
            return ResponseEntity.ok("Evaluation deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the evaluation.");
        }
    }


}
