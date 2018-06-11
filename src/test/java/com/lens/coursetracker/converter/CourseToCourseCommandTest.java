package com.lens.coursetracker.converter;

import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.model.Course;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseToCourseCommandTest {
    public static final Integer ID_VALUE = 1;
    public static final String URL_VALUE = "testUrl";

    CourseToCourseCommand converter;

    @Before
    public void setUp() throws Exception{
        converter = new CourseToCourseCommand();
    }

    @Test
    public void convert() throws Exception{
        Course course = new Course();
        course.setId(ID_VALUE);
        course.setUrl(URL_VALUE);

        CourseCommand courseCommand = converter.convert(course);

        assertEquals(ID_VALUE,courseCommand.getId());
        assertEquals(URL_VALUE,courseCommand.getUrl());


    }
}