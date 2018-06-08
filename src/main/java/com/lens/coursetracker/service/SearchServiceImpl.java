package com.lens.coursetracker.service;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.converter.TagCommandToTag;
import com.lens.coursetracker.converter.TagToTagCommand;
import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.model.MyCourse;
import com.lens.coursetracker.model.Tag;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SearchServiceImpl implements SearchService {

    private final CourseService courseService;
    private final MyCourseService myCourseService;


    @Autowired
    public SearchServiceImpl(CourseService courseService,MyCourseService myCourseService) {
        this.courseService = courseService;
        this.myCourseService = myCourseService;
    }

    @Override
    public Set<Course> searchCoursesByTag(int tagId) {
        Set<Course> courses = courseService.findAll();
        Set<Course> matchingCourses = new HashSet<>();
        for(Course course : courses){
            Set<Tag> tags = course.getTags();
            for(Tag tag : tags){
                if(tag.getId() == tagId){
                    matchingCourses.add(course);
                    break;
                }
            }
        }
        return matchingCourses;
    }

    @Override
    public Set<MyCourse> searchMyCoursesByTag(int tagId) {
        Set<MyCourse> myCourses = myCourseService.findAll();
        Set<MyCourse> matchingMyCourses = new HashSet<>();
        for(MyCourse myCourse : myCourses){
            Course course = myCourse.getCourse();
            Set<Tag> tags = course.getTags();
            for(Tag tag : tags){
                if(tag.getId() == tagId){
                    matchingMyCourses.add(myCourse);
                    break;
                }
            }
        }
        return matchingMyCourses;
    }
}
