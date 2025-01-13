package com.example.demo.controller.Faculties;


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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Faculties.Faculties;
import com.example.demo.service.Faculties.FacultiesService;

@Controller
@RequestMapping("/faculties")
public class FacultiesController {
	
	@Autowired
	public FacultiesService facultiesService;
	
	 public FacultiesController(FacultiesService facultiesService) {
	        this.facultiesService = facultiesService;
	    }
	@GetMapping("/view")
	  public ResponseEntity<List<Faculties>> getFaculties() {
        List<Faculties> faculties = this.facultiesService.getFaculties();
       return ResponseEntity.ok(faculties);
    }
	 @GetMapping("")
	    public String getFaculties(Model model) {
//	        // Lấy danh sách faculties từ Service
//	        List<Faculties> faculties = facultiesService.getFaculties();
//	        // Đưa danh sách vào Model để truyền tới view
//	        if(faculties.isEmpty()) {
//	        	 model.addAttribute("message", "No patients found");
//	        }
//	        else {
//	        	  model.addAttribute("faculties", faculties);
//	        }
//	        // Trả về file HTML tên là "faculties.html"
	        return "faculties/form_faculties";
	    }
	
//	@GetMapping("/{faId}")
//	public Faculties getFaculties(@PathVariable long faId) {
//		return this.facultiesService.getFaculties(faId);
//	}
	 // Lấy thông tin khoa theo ID
    @GetMapping("/{faId}")
    public ResponseEntity<Faculties> getFaculties(@PathVariable long faId) {
        Faculties faculty = this.facultiesService.getFaculties(faId);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(faculty);
    }
	
    
 // Thêm mới khoa
//    @PostMapping("/save")
//    public ResponseEntity<Faculties> addFaculties(@RequestBody Faculties fa) {
//        Faculties faculties = this.facultiesService.addFaculties(fa);
//        return ResponseEntity.status(HttpStatus.CREATED).body(faculties);
//    }	
    @PostMapping("/save")
	  public String addFaculties(@ModelAttribute Faculties fa) {
	      Faculties faculties = this.facultiesService.addFaculties(fa);
	      return "redirect:/faculties";
	  }	
    
 // Cập nhật thông tin khoa
    @PutMapping("/{faId}")
    public ResponseEntity<Faculties> updateFaculties(@PathVariable long faId, @RequestBody Faculties fa) {
        Faculties updatedFaculty = this.facultiesService.updateFaculties(faId, fa);
        if (updatedFaculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(updatedFaculty);
    }
    
 // Xóa khoa
    @DeleteMapping("/{faId}")
    public ResponseEntity<Void> deleteFaculties(@PathVariable long faId) {
        try {
            this.facultiesService.deleteFaculties(faId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
