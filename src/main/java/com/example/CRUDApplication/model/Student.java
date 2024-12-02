package com.example.CRUDApplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="Students")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;
}
