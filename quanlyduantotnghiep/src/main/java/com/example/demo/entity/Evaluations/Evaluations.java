package com.example.demo.entity.Evaluations;

import java.math.BigDecimal;
import java.util.Date;

import com.example.demo.entity.Projects.Projects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "evaluations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Evaluations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Projects project;
	
	@Column(name = "evaluation_date")
	private Date evaluation_date;
	
	@Column(name = "comments")
	private String comments;
	
	@Column(name = "score")
    private BigDecimal score;
	
	@Column(name = "board_name")
	private String board_name;
	
	public Evaluations() {
		
	}

	public Evaluations(Long id, Projects project, Date evaluation_date, String comments, BigDecimal score,
			String board_name) {
		super();
		Id = id;
		this.project = project;
		this.evaluation_date = evaluation_date;
		this.comments = comments;
		this.score = score;
		this.board_name = board_name;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Projects getProject() {
		return project;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

	public Date getEvaluation_date() {
		return evaluation_date;
	}

	public void setEvaluation_date(Date evaluation_date) {
		this.evaluation_date = evaluation_date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getBoard_name() {
		return board_name;
	}

	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	
	
}
