package com.lens.coursetracker.service;

import com.lens.coursetracker.converter.CourseCommandToCourse;
import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.repository.CourseRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CourseServiceImplTest {
    private static final Integer COURSE_ID = 1;
    private static final String COURSE_TITLE = "CourseTitle";
    private static final String COURSE_URL = "CourseUrl";
    private static final Integer COURSE_ID_2 = 2;
    private static final String COURSE_TITLE_2 = "CourseTitle2";
    private static final String COURSE_URL_2 = "CourseUrl2";

    @Mock
    CourseRepository courseRepository;

    @Mock
    CourseCommandToCourse courseCommandToCourse;

    CourseServiceImpl courseService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        courseService = new CourseServiceImpl(courseRepository,courseCommandToCourse);
    }

    @Test
    public void findAll() {
        Course course = new Course();
        course.setId(COURSE_ID);
        course.setUrl(COURSE_URL);
        course.setTitle(COURSE_TITLE);

        Course course2 = new Course();
        course2.setId(COURSE_ID_2);
        course2.setUrl(COURSE_URL_2);
        course2.setTitle(COURSE_TITLE_2);

        List<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course2);

        when(courseRepository.findAll()).thenReturn(courses);

        Set<Course> courseSet = courseService.findAll();

        assertEquals(2,courseSet.size());
        verify(courseRepository,times(1)).findAll();
    }

    @Test
    public void saveCourse() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void getCourse() {
        Course course = new Course();
        course.setId(COURSE_ID);
        course.setUrl(COURSE_URL);
        course.setTitle(COURSE_TITLE);

        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(course));

        Course courseFound = courseService.getCourse(1);

        assertEquals(COURSE_ID, courseFound.getId());
        assertEquals(COURSE_URL,courseFound.getUrl());
        assertEquals(COURSE_TITLE,courseFound.getTitle());
        verify(courseRepository,times(1)).findById(anyInt());
    }
}