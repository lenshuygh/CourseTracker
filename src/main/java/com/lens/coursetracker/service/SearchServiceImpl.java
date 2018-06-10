package com.lens.coursetracker.service;

import com.lens.coursetracker.CoursetrackerApplication;
import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.model.MyCourse;
import com.lens.coursetracker.model.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SearchServiceImpl implements SearchService {

    private final CourseService courseService;
    private final MyCourseService myCourseService;

    @Autowired
    public SearchServiceImpl(CourseService courseService, MyCourseService myCourseService) {
        this.courseService = courseService;
        this.myCourseService = myCourseService;
    }

    private static Logger logger = LogManager.getLogger(CoursetrackerApplication.class);

    @Override
    public Set<Course> searchCoursesByTag(int tagId) {
        Set<Course> courses = courseService.findAll();
        Set<Course> matchingCourses = new HashSet<>();
        for (Course course : courses) {
            Set<Tag> tags = course.getTags();
            for (Tag tag : tags) {
                if (tag.getId() == tagId) {
                    matchingCourses.add(course);
                    break;
                }
            }
        }
        logger.info("searchCoursesByTag() -> " + tagId);
        return matchingCourses;
    }

    @Override
    public Set<MyCourse> searchMyCoursesByTag(int tagId) {
        Set<MyCourse> myCourses = myCourseService.findAll();
        Set<MyCourse> matchingMyCourses = new HashSet<>();
        for (MyCourse myCourse : myCourses) {
            Course course = myCourse.getCourse();
            Set<Tag> tags = course.getTags();
            for (Tag tag : tags) {
                if (tag.getId() == tagId) {
                    matchingMyCourses.add(myCourse);
                    break;
                }
            }
        }
        logger.info("searchMyCoursesByTag() -> " + tagId);
        return matchingMyCourses;
    }
}
