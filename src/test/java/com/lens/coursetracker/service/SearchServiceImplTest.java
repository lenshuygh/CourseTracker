package com.lens.coursetracker.service;

import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.model.MyCourse;
import com.lens.coursetracker.model.Tag;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchServiceImplTest {

    @Mock
    CourseService courseService;

    @Mock
    MyCourseService myCourseService;

    SearchServiceImpl searchService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        searchService = new SearchServiceImpl(courseService,myCourseService);
    }

    @Test
    public void searchCoursesByTag() {
        Set<Course> courseSet = new HashSet<>();
        Course c1 = new Course();
        c1.setId(1);
        Tag tag = new Tag();
        tag.setId(10);
        tag.setTagName("Ten");
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(tag);
        c1.setTags(tagSet);

        Course c2 = new Course();
        c2.setId(2);
        Tag tag2 = new Tag();
        tag2.setId(5);
        tag2.setTagName("Five");
        Set<Tag> tagSet2 = new HashSet<>();
        tagSet2.add(tag2);
        c2.setTags(tagSet2);

        courseSet.add(c1);
        courseSet.add(c2);

        when(courseService.findAll()).thenReturn(courseSet);

        Set<Course> foundCourses = searchService.searchCoursesByTag(10);

        assertEquals(1,foundCourses.size());

        foundCourses = searchService.searchCoursesByTag(0);

        assertEquals(0,foundCourses.size());

    }

    @Test
    public void searchMyCoursesByTag() {
        Course c1 = new Course();
        c1.setId(1);
        Tag tag = new Tag();
        tag.setId(10);
        tag.setTagName("Ten");
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(tag);
        c1.setTags(tagSet);

        Course c2 = new Course();
        c2.setId(2);
        Tag tag2 = new Tag();
        tag2.setId(5);
        tag2.setTagName("Five");
        Set<Tag> tagSet2 = new HashSet<>();
        tagSet2.add(tag2);
        c2.setTags(tagSet2);

        MyCourse myCourse1 = new MyCourse();
        myCourse1.setCourse(c1);

        MyCourse myCourse2 = new MyCourse();
        myCourse2.setCourse(c2);

        Set<MyCourse> myCourseSet = new HashSet<>();

        myCourseSet.add(myCourse1);
        myCourseSet.add(myCourse2);

        when(myCourseService.findAll()).thenReturn(myCourseSet);

        Set<MyCourse> foundMyCourses = searchService.searchMyCoursesByTag(5);

        assertEquals(1,foundMyCourses.size());

        foundMyCourses = searchService.searchMyCoursesByTag(20);

        assertEquals(0,foundMyCourses.size());
    }
}