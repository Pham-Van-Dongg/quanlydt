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
		return "faculties/form_faculties";
	}

//	@GetMapping("/{faId}")
//	public Faculties getFaculties(@PathVariable long faId) {
//		return this.facultiesService.getFaculties(faId);
//	}
//	@GetMapping("/details")
//	public String projectDetails(Model model) {
//		return "faculties/form_addFaculties";
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
	@PostMapping("/save")
	public ResponseEntity<String> addFaculties(@RequestBody Faculties fa) {
		try {
			Faculties faculties = this.facultiesService.addFaculties(fa);
			// Trả về thông báo thành công
			return ResponseEntity.status(HttpStatus.CREATED)
					.body("Tạo mới khoa thành công với ID: " + faculties.getId());
		} catch (Exception e) {
			// Trả về thông báo lỗi
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Tạo mới khoa thất bại. Lỗi: " + e.getMessage());
		}
	}

	// Cập nhật thông tin khoa
	@PutMapping("/update/{faId}")
	public ResponseEntity<String> updateFaculties(@PathVariable long faId, @RequestBody Faculties fa) {
		try {
			Faculties updatedFaculty = this.facultiesService.updateFaculties(faId, fa);
			if (updatedFaculty == null) {
				// Trả về thông báo không tìm thấy
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Không tìm thấy khoa với ID: " + faId + " để cập nhật.");
			}
			// Trả về thông báo thành công
			return ResponseEntity.ok("Cập nhật thành công khoa với ID: " + faId);
		} catch (Exception e) {
			// Trả về thông báo lỗi
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Cập nhật thất bại cho khoa với ID: " + faId + ". Lỗi: " + e.getMessage());
		}
	}

	// Xóa khoa
	@DeleteMapping("/delete/{faId}")
	public ResponseEntity<String> deleteFaculties(@PathVariable long faId) {
		try {
			// Gọi service để xóa khoa
			this.facultiesService.deleteFaculties(faId);

			// Trả về thông báo thành công
			return ResponseEntity.ok("Xóa thành công khoa có ID: " + faId);
		} catch (Exception e) {
			// Trả về thông báo lỗi kèm thông tin lỗi chi tiết
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Xóa thất bại khoa có ID: " + faId + ". Lỗi: " + e.getMessage());
		}
	}

}
