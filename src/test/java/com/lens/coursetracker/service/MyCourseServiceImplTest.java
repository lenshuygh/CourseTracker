package com.lens.coursetracker.service;

import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.model.MyCourse;
import com.lens.coursetracker.repository.CourseRepository;
import com.lens.coursetracker.repository.MyCourseRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MyCourseServiceImplTest {
    public static final Integer MYCOURSE_ID = 1;
    public static final String MYCOURSE_NOTES = "my course notes";
    public static final Boolean MYCOURSE_COMPLETED = false;

    public static final Integer MYCOURSE_ID_2 = 2;
    public static final String MYCOURSE_NOTES_2 = "my course notes 2";
    public static final Boolean MYCOURSE_COMPLETED_2 = true;

    @Mock
    MyCourseRepository myCourseRepository;

    @Mock
    CourseRepository courseRepository;

    MyCourseServiceImpl myCourseService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        myCourseService = new MyCourseServiceImpl(myCourseRepository,courseRepository);
    }

    @Test
    public void getMyCourse() {
        MyCourse myCourse = new MyCourse();
        myCourse.setId(MYCOURSE_ID);
        myCourse.setNotes(MYCOURSE_NOTES);
        myCourse.setCompleted(MYCOURSE_COMPLETED);

        when(myCourseRepository.getOne(anyInt())).thenReturn(myCourse);

        MyCourse myCourseFound = myCourseService.getMyCourse(1);

        assertEquals(MYCOURSE_ID,myCourseFound.getId());
        assertEquals(MYCOURSE_NOTES,myCourseFound.getNotes());
        assertEquals(MYCOURSE_COMPLETED,myCourseFound.getCompleted());
        verify(myCourseRepository,times(1)).getOne(anyInt());
    }

    @Test
    public void findAll() {
        MyCourse myCourse = new MyCourse();
        myCourse.setId(MYCOURSE_ID);
        myCourse.setNotes(MYCOURSE_NOTES);
        myCourse.setCompleted(MYCOURSE_COMPLETED);

        MyCourse myCourse2 = new MyCourse();
        myCourse2.setId(MYCOURSE_ID_2);
        myCourse2.setNotes(MYCOURSE_NOTES_2);
        myCourse2.setCompleted(MYCOURSE_COMPLETED_2);

        List<MyCourse> myCourseList = new ArrayList<>();

        myCourseList.add(myCourse);
        myCourseList.add(myCourse2);

        when(myCourseRepository.findAll()).thenReturn(myCourseList);

        Set<MyCourse> myCourseSet = myCourseService.findAll();

        assertEquals(2,myCourseSet.size());
    }

}