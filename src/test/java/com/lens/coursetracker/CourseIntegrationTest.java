package com.lens.coursetracker;


import com.lens.coursetracker.converter.CourseToCourseCommand;
import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.service.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class CourseIntegrationTest {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseToCourseCommand courseToCourseCommand;

    @Transactional
    @Test
    public void testEnterNewCourse(){
        Course course = new Course();
        course.setTitle("courseTitle");
        course.setUrl("courseUrl");

        courseService.saveCourse(courseToCourseCommand.convert(course));

        assertEquals(courseService.getCourse(4).getTitle(),course.getTitle());
    }

    @Transactional
    @Test
    public void testDeleteCourse(){
        Course course = new Course();
        course.setTitle("courseTitle");
        course.setUrl("courseUrl");
        courseService.saveCourse(courseToCourseCommand.convert(course));

        int courseCount = courseService.findAll().size();

        courseService.deleteById(4);

        assertNotEquals(courseCount,courseService.findAll().size());
    }

    @Transactional
    @Test
    public void testUpdateCourse(){
        Course course = courseService.getCourse(1);
        String originalTitle = course.getTitle();

        course.setTitle("newTitle");
        courseService.saveCourse(courseToCourseCommand.convert(course));

        assertNotEquals(originalTitle,courseService.getCourse(1));
    }

    @Transactional
    @Test
    public void testReadCourse(){
        Course course = courseService.getCourse(1);

        assertNotNull(course);
    }

}
