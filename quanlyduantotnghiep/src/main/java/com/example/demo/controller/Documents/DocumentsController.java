package com.example.demo.controller.Documents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@GetMapping("/view")
	public ResponseEntity<List<Documents>> getDocuments() {
		List<Documents> documents = this.documentsService.getDocuments();
		return ResponseEntity.ok(documents);
	}

	// Lấy thông tin đề tài theo ID
	@GetMapping("/{docId}")
	public ResponseEntity<Documents> getDocuments(@PathVariable long docId) {
		Documents documents = this.documentsService.getDocuments(docId);
		if (documents == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(documents);
	}

//    @PostMapping("/save")
//	  public String addDocuments(@ModelAttribute Documents doc) {
//    	Documents documents = this.documentsService.addDocuments(doc);
//	      return "redirect:/documents";
//	  }	
	@GetMapping("")
	public String getDocuments(Model model) {
		return "documents/form_documents";
	}

	@PostMapping("/save")
	public ResponseEntity<String> addDocuments(@RequestBody Documents documents) {
		// Kiểm tra xem dự án có tồn tại không
		Projects project = projectsRepository.findById(documents.getProjects().getId())
				.orElseThrow(() -> new RuntimeException("Project not found"));

		// Gán đối tượng project vào documents
		documents.setProjects(project);

		// Lưu đối tượng documents vào cơ sở dữ liệu
		documentsService.addDocuments(documents);

		return ResponseEntity.ok("Document saved successfully");
	}

// Cập nhật thông tin khoa
	@PutMapping("/{docId}")
	public ResponseEntity<Documents> updateFaculties(@PathVariable long docId, @RequestBody Documents doc) {
		Documents documents = this.documentsService.updateDocuments(docId, doc);
		if (documents == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(documents);
	}

// Xóa de tai
	@DeleteMapping("/{docId}")
	public ResponseEntity<Void> deleteDocuments(@PathVariable long docId) {
		try {
			this.documentsService.deleteDocuments(docId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
