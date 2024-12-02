package com.example.CRUDApplication.controller;
import com.example.CRUDApplication.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CRUDApplication.repo.CourseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {
    @Autowired
    private CourseRepo courseRepo;

    @GetMapping("/getCourses")
    public ResponseEntity<List<Course>> getAllCourses(){
        try {
            List<Course> courseList = new ArrayList<>();
            courseRepo.findAll().forEach(courseList::add);

            if (courseList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(courseList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> courseData = courseRepo.findById(id);
        if (courseData.isPresent()) {
            return new ResponseEntity<>(courseData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course courseObj = courseRepo.save(course);

        return new ResponseEntity<>(courseObj, HttpStatus.OK);
    }

    @PostMapping("/updateCourseById/{id}")
    public ResponseEntity<Course> updateCourseById(@PathVariable Long id, @RequestBody Course newCourseData) {
        Optional<Course> oldCourseData = courseRepo.findById(id);

        if (oldCourseData.isPresent()) {
           Course updatedCourseData = oldCourseData.get();
           updatedCourseData.setName(newCourseData.getName());
           updatedCourseData.setDescription(newCourseData.getDescription());
            updatedCourseData.setType(newCourseData.getType());
            updatedCourseData.setStartDate(newCourseData.getStartDate());
            updatedCourseData.setEndDate(newCourseData.getEndDate());

           Course courseObj = courseRepo.save(updatedCourseData);
           return new ResponseEntity<>(courseObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteCourseById/{id}")
    public ResponseEntity<HttpStatus> deleteCourseById(@PathVariable Long id) {
        courseRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/courses/{courseId}/enroll")
    public ResponseEntity<HttpStatus> enrollCourse(@PathVariable Long courseId) {

    }
}
