package com.lens.coursetracker.service;

import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.model.MyCourse;

import java.util.Set;

public interface SearchService {
    Set<Course> searchCoursesByTag(int tagId);


    Set<MyCourse> searchMyCoursesByTag(int tagId);
}
