package com.lens.coursetracker;

import com.lens.coursetracker.model.MyCourse;
import com.lens.coursetracker.service.CourseService;
import com.lens.coursetracker.service.MyCourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class MyCourseIntegrationTest_IT {

    @Autowired
    MyCourseService myCourseService;

    @Autowired
    CourseService courseService;

    @Transactional
    @Test
    public void enterNewMyCourseTest(){
        MyCourse myCourse = new MyCourse();
        myCourse.setCourse(courseService.getCourse(1));
        myCourse.setNotes("myCourseNotes");

        int size = myCourseService.findAll().size();

        myCourseService.save(myCourse);

        assertEquals(size+1,myCourseService.findAll().size());
    }

    @Transactional
    @Test
    public void testReadMyCourse(){
        MyCourse myCourse = myCourseService.getMyCourse(1);

        assertNotNull(myCourse);
    }

    @Transactional
    @Test
    public void testUpdateMyCourse(){
        MyCourse myCourse = myCourseService.getMyCourse(1);

        String notes = myCourse.getNotes();

        myCourse.setNotes("newNotes");

        myCourseService.save(myCourse);

        assertNotEquals(notes,myCourseService.getMyCourse(1).getNotes());

    }


}
