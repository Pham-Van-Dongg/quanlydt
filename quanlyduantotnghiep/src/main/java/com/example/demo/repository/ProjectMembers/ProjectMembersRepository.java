package com.example.demo.repository.ProjectMembers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProjectMembers.ProjectMembers;

import java.util.List;

public interface ProjectMembersRepository extends JpaRepository<ProjectMembers, Long> {
    List<ProjectMembers> findByProject_Id(long prbId);
}
