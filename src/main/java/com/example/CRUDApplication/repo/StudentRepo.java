package com.example.CRUDApplication.repo;

import com.example.CRUDApplication.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {

}
