package com.example.demo.entity.documents;

import java.sql.Time;
import java.sql.Timestamp;

import com.example.demo.entity.Projects.Projects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "documents")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Documents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@OneToOne
	@JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
	private Projects projects;
	private String file_name;
	private String file_path;
	private Timestamp uploaded_at;
	
	public Documents() {
		
	}

	public Documents(Long id, Projects projects, String file_name, String file_path, Timestamp uploaded_at) {
		super();
		Id = id;
		this.projects = projects;
		this.file_name = file_name;
		this.file_path = file_path;
		this.uploaded_at = uploaded_at;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Projects getProjects() {
		return projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public Timestamp getUploaded_at() {
		return uploaded_at;
	}

	public void setUploaded_at(Timestamp uploaded_at) {
		this.uploaded_at = uploaded_at;
	}
	
}
