package com.example.demo.service.Faculties;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.Faculties.FacultiesRepository;
import com.example.demo.entity.Faculties.Faculties;

@Service
public class FacultiesServiceImpl implements FacultiesService{

	@Autowired
	private FacultiesRepository facultiesRepository;
	
	@Override
	public List<Faculties> getFaculties() {
		// TODO Auto-generated method stub
		return facultiesRepository.findAll();
	}

	@Override
	public Faculties addFaculties(Faculties fa) {
		facultiesRepository.save(fa);
		return fa;
	}

	@Override
	public Faculties getFaculties(long faId) {
		Faculties fa = facultiesRepository.findById(faId).get();
		return fa;
	}
	
	@Override
	public Faculties updateFaculties(long faId, Faculties fa) {
	    // Tìm đối tượng theo ID
	    Faculties existingFaculty = facultiesRepository.findById(faId).orElse(null);
	    
	    if (existingFaculty == null) {
	        throw new RuntimeException("Faculty not found with ID: " + faId); // Hoặc trả về null
	    }

	    // Cập nhật thông tin
	    existingFaculty.setName(fa.getName());
	    existingFaculty.setDescription(fa.getDescription());

	    // Lưu lại đối tượng đã cập nhật
	    return facultiesRepository.save(existingFaculty);
	}


	@Override
	public Faculties deleteFaculties(long id) {
		facultiesRepository.delete(facultiesRepository.findById(id).get());
		return null;
	}


}
