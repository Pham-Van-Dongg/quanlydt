package com.example.demo.entity.ProjectMembers;
import com.example.demo.entity.Projects.Projects;
import com.example.demo.entity.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
@Entity
@Table(name = "projectmembers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProjectMembers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Projects project;
	
	@Column(name = "masinhvien")
	private String masinhvien;
	
	@Column(name = "hovaten")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private Role role;
	public enum Role {
		Leader, Member, Supporter
	}
	 
	 public ProjectMembers() {
		 //constructor mặc định
	 }

	public ProjectMembers(Long id, Projects project, String masinhvien, String name, Role role) {
		super();
		this.id = id;
		this.project = project;
		this.masinhvien = masinhvien;
		this.name = name;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Projects getProject() {
		return project;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

	public String getMasinhvien() {
		return masinhvien;
	}

	public void setMasinhvien(String masinhvien) {
		this.masinhvien = masinhvien;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}	
	 
}
