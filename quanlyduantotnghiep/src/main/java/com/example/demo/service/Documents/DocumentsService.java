package com.example.demo.service.Documents;
import java.util.List;
import com.example.demo.entity.documents.Documents;

public interface DocumentsService {

	public List<Documents> getDocuments();

	public Documents addDocuments(Documents doc);

	public Documents getDocuments(long docId);

	public Documents updateDocuments(long docId, Documents doc);

	public Documents deleteDocuments(long id);
}
