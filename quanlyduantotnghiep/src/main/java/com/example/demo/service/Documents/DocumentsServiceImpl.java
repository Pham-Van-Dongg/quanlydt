package com.example.demo.service.Documents;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.documents.Documents;
import com.example.demo.repository.Documents.DocumentsRepository;

@Service
public class DocumentsServiceImpl implements DocumentsService{

	@Autowired 
	private DocumentsRepository documentsRepository;
	@Override
	public List<Documents> getDocuments() {
		// TODO Auto-generated method stub
		return documentsRepository.findAll();
	}

	@Override
	public Documents addDocuments(Documents doc) {
		// TODO Auto-generated method stub
		documentsRepository.save(doc);
		return doc;
	}

	@Override
	public Documents getDocuments(long docId) {
		// TODO Auto-generated method stub
		Documents doc = documentsRepository.findById(docId).get();
		return doc;
	}

	@Override
	public Documents updateDocuments(long docId, Documents doc) {
		// TODO Auto-generated method stub
		Documents exitDocuments = documentsRepository.findById(docId).orElse(null);
		if (exitDocuments == null) {
	        throw new RuntimeException("Faculty not found with ID: " + docId); // Hoặc trả về null
	    }

	    // Cập nhật thông tin
		//exitDocuments.setName(doc.getFile_name());
		//exitDocuments.setDescription(doc.getDescription());
		
		exitDocuments.setProjects(doc.getProjects());
		exitDocuments.setFile_name(doc.getFile_name());
		exitDocuments.setFile_path(doc.getFile_path());
		
		exitDocuments.setUploaded_at(doc.getUploaded_at());


	    // Lưu lại đối tượng đã cập nhật
	    return documentsRepository.save(exitDocuments);
	}

	@Override
	public Documents deleteDocuments(long id) {
		// TODO Auto-generated method stub
		documentsRepository.delete(documentsRepository.findById(id).get());
		return null;
	}

	
	
	
}
