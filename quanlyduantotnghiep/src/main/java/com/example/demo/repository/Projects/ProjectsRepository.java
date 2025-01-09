package com.example.demo.repository.Projects;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Projects.Projects;

public interface ProjectsRepository extends JpaRepository<Projects, Long>{

}
