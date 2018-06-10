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

import java.util.HashSet;
import java.util.Set;

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
    public Course findCourse(int id) {
        logger.info("findCourse() -> " + id);
        return courseRepository.getOne(id);
    }

    @Override
    public Set<Course> findAll() {
        logger.info("findAll()");
        return new HashSet<>(courseRepository.findAll());
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
        courseRepository.deleteById(id);
    }

    @Override
    public Course getCourse(int id) {
        logger.info("getCourse() -> " + id);
        return courseRepository.getOne(id);
    }
}
