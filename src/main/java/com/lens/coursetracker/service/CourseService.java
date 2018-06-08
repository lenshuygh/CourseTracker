package com.lens.coursetracker.service;

import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.model.Course;

import java.util.Set;

public interface CourseService {
    void saveCourse(CourseCommand courseCommand);

    Course findCourse(int id);

    Set<Course> findAll();

    void deleteById(int id);

    Course getCourse(int id);
}
