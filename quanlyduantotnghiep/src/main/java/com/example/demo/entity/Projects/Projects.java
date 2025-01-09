package com.example.demo.entity.Projects;

import java.time.LocalDate;

import com.example.demo.entity.Faculties.Faculties;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
@Entity
@Table(name = "projects")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Projects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 255)
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	@Column(name = "start_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startday;
	
	@Column(name = "end_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endday;
	
	@Enumerated(EnumType.STRING) // Lưu giá trị enum dưới dạng chuỗi ('Pending', 'In Progress', ...)
    @Column(name = "status", nullable = false)
    private Status status;
	public enum Status {
		Pending, InProgress, Completed, Cancelled
    }
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    private Faculties faculty;  // Thay vì faculty_id, khai báo đối tượng Faculties
	
	public Faculties getFaculty() {
		return faculty;
	}

	public Projects() {
        // constructor mặc định
    }

	public Projects(Long id, String name, String description, LocalDate startday, LocalDate endday, Status status,
			Faculties faculty) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startday = startday;
		this.endday = endday;
		this.status = status;
		this.faculty = faculty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartday() {
		return startday;
	}

	public void setStartday(LocalDate startday) {
		this.startday = startday;
	}

	public LocalDate getEndday() {
		return endday;
	}

	public void setEndday(LocalDate endday) {
		this.endday = endday;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setFaculty(Faculties faculty) {
		this.faculty = faculty;
	}
	

	
	
}
