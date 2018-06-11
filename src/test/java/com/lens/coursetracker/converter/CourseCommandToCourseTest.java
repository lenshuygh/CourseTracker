package com.lens.coursetracker.converter;

import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.model.Course;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

public class CourseCommandToCourseTest {

    public static final Integer ID_VALUE = 1;
    public static final String URL_VALUE = "testUrl";


    CourseCommandToCourse converter;

    @Before
    public void setUp() throws Exception{
        converter = new CourseCommandToCourse();
    }

    @Test
    public void convert() throws Exception{
        CourseCommand courseCommand = new CourseCommand();
        courseCommand.setId(ID_VALUE);
        courseCommand.setUrl(URL_VALUE);

        Course course = converter.convert(courseCommand);

        assertEquals(ID_VALUE,course.getId());
        assertEquals(URL_VALUE,course.getUrl());
    }
}