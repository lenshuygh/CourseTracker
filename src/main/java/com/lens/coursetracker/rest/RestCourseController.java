package com.lens.coursetracker.rest;

import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.converter.CourseToCourseCommand;
import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.service.CourseService;
import com.lens.coursetracker.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class RestCourseController {

    @Autowired
    private final CourseService courseService;

    @Autowired
    private final CourseToCourseCommand courseToCourseCommand;

    public RestCourseController(CourseService courseService,CourseToCourseCommand courseToCourseCommand) {
        this.courseService = courseService;
        this.courseToCourseCommand = courseToCourseCommand;
    }

    @GetMapping("/courses")
    public Collection<Course> getCourses(){
        return courseService.findAll();
    }

    @GetMapping("course/{id}")
    public Course getCourse(@PathVariable int id){
        return courseService.getCourse(id);
    }

    @PostMapping("/course")
    public Collection<Course> addCourse(@RequestBody CourseCommand courseCommand){
        courseService.saveCourse(courseCommand);
        return courseService.findAll();
    }

    @PostMapping("/course/update/{id}")
    public Collection<Course> updateCourse(@PathVariable int id,@RequestBody CourseCommand courseCommand){
        CourseCommand courseCommandToUpdate = courseToCourseCommand.convert(courseService.getCourse(id));
        courseCommandToUpdate.setTitle(courseCommand.getTitle());
        courseCommandToUpdate.setAuthor(courseCommand.getAuthor());
        courseCommandToUpdate.setUrl(courseCommand.getUrl());
        courseCommandToUpdate.setSubjects(courseCommand.getSubjects());
        courseCommandToUpdate.setTags(courseCommand.getTags());
        courseCommandToUpdate.setPublishdate(courseCommand.getPublishdate());
        courseService.saveCourse(courseCommandToUpdate);
        return courseService.findAll();
    }

    @GetMapping("/course/delete/{id}")
    public Collection<Course> deleteCourse(@PathVariable int id){
        courseService.deleteById(id);
        return courseService.findAll();
    }
}
