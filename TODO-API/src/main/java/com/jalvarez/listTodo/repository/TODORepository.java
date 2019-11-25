package com.jalvarez.listTodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jalvarez.listTodo.model.TODO;

@Repository
public interface TODORepository extends JpaRepository<TODO, Long> {
	
}
