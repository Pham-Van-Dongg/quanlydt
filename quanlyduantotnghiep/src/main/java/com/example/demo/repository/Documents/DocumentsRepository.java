package com.example.demo.repository.Documents;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.documents.Documents;

public interface DocumentsRepository extends JpaRepository<Documents, Long>{

}
