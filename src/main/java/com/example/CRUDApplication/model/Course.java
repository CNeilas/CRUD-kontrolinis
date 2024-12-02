package com.example.CRUDApplication.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name="Courses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String type;
    private String startDate;
    private String endDate;
    @OneToMany
    private List<Student> students;
}
