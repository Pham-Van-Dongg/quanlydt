package com.example.demo.service.Faculties;
import java.util.List;
import com.example.demo.entity.Faculties.Faculties;

public interface FacultiesService {
	
	public List<Faculties> getFaculties();
	
	public Faculties addFaculties(Faculties fa);
	
	public Faculties getFaculties(long faId);
	
	public Faculties updateFaculties( long faId,Faculties fa);
	
	public Faculties deleteFaculties(long id);
}
