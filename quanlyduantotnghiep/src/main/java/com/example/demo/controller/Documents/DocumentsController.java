package com.example.demo.controller.Documents;
  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Projects.Projects;
import com.example.demo.entity.documents.Documents;
import com.example.demo.repository.Projects.ProjectsRepository;
import com.example.demo.service.Documents.DocumentsService;

@Controller
@RequestMapping("/documents")
public class DocumentsController {

    @Autowired
    public DocumentsService documentsService;

    @Autowired
    private ProjectsRepository projectsRepository;

    public DocumentsController(DocumentsService documentsService) {
        this.documentsService = documentsService;
    }

    @GetMapping("/create")
    public String createProject(Model model) {
        return "documents/create";
    }

    @GetMapping("/update")
    public String createUpdate(Model model) {
        return "documents/update";
    }
    
    @GetMapping("/view")
    public ResponseEntity<List<Documents>> getDocuments() {
        List<Documents> documents = this.documentsService.getDocuments();
        if (documents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(documents);
        }
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{docId}")
    public ResponseEntity<?> getDocuments(@PathVariable long docId) {
        Documents documents = this.documentsService.getDocuments(docId);
        if (documents == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found");
        }
        return ResponseEntity.ok(documents);
    }

    @GetMapping("")
    public String getDocuments(Model model) {
        return "documents/form_documents";
    }

    @PostMapping("/create")
    public ResponseEntity<String> addDocuments(@RequestBody Documents documents) {
        try {
            // Kiểm tra xem dự án có tồn tại không
            Projects project = projectsRepository.findById(documents.getProjects().getId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));

            // Gán đối tượng project vào documents
            documents.setProjects(project);

            // Lưu đối tượng documents vào cơ sở dữ liệu
            documentsService.addDocuments(documents);

            return ResponseEntity.ok("Document saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save document: " + e.getMessage());
        }
    }

    @PutMapping("/{docId}")
    public ResponseEntity<?> updateFaculties(@PathVariable long docId, @RequestBody Documents doc) {
        try {
            Documents documents = this.documentsService.updateDocuments(docId, doc);
            if (documents == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found");
            }
            return ResponseEntity.ok("Document updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update document: " + e.getMessage());
        }
    }

    @DeleteMapping("/{docId}")
    public ResponseEntity<String> deleteDocuments(@PathVariable long docId) {
        try {
            this.documentsService.deleteDocuments(docId);
            return ResponseEntity.ok("Document deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete document: " + e.getMessage());
        }
    }
}
