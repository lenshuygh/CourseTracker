package com.lens.coursetracker.service;

import com.lens.coursetracker.CoursetrackerApplication;
import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.converter.CourseCommandToCourse;
import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.repository.CourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseCommandToCourse courseCommandToCourse;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseCommandToCourse courseCommandToCourse) {
        this.courseRepository = courseRepository;
        this.courseCommandToCourse = courseCommandToCourse;
    }

    private static Logger logger = LogManager.getLogger(CoursetrackerApplication.class);

    @Override
    public Set<Course> findAll() {
        logger.info("findAll()");
        List<Course> foundCourses = courseRepository.findAll();
        SortedSet<Course> sortedSet = new TreeSet<Course>(foundCourses);
        return sortedSet;
    }

    @Override
    public void saveCourse(CourseCommand courseCommand) {
        Course newCourse = courseRepository.save(courseCommandToCourse.convert(courseCommand));
        logger.info("saveCourse() -> newCourse -> " + newCourse);
        courseRepository.save(newCourse);
    }

    @Override
    public void deleteById(int id) {
        logger.info("deleteById() -> " + id);
        if(!courseRepository.findById(id).isPresent()){
            throw new EntityNotFoundException("Course not found for id: " + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    public Course getCourse(int id) {
        logger.info("getCourse() -> " + id);
        Optional<Course> theCourse = courseRepository.findById(id);
        if (!theCourse.isPresent()) {
            throw new EntityNotFoundException("Course not found for id: " + id);
        }
        return theCourse.get();
    }
}
