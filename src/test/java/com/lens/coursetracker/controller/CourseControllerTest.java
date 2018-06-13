package com.lens.coursetracker.controller;

import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.converter.CourseCommandToCourse;
import com.lens.coursetracker.service.CourseService;
import com.lens.coursetracker.service.MyCourseService;
import com.lens.coursetracker.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CourseControllerTest {

    @Mock
    CourseService courseService;

    @Mock
    TagService tagService;

    @Mock
    MyCourseService myCourseService;

    @Mock
    HttpSession session;

    @Mock
    CourseCommandToCourse courseCommandToCourse;

    MockMvc mockMvc;

    CourseController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new CourseController(courseService, tagService, myCourseService, session, courseCommandToCourse);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getCourses() throws Exception {
        mockMvc.perform(get("/getCourses"))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseOverview"))
                .andExpect(model().attributeExists("courses"));
        verify(courseService,times(1)).findAll();

    }

    @Test
    public void showFormStandard() throws Exception {
        mockMvc.perform(get("/courseForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseForm"))
                .andExpect(model().attributeExists("taglist"))
                .andExpect(model().attributeExists("course"));
        verify(tagService,times(1)).findAll();

    }

    @Test
    public void saveOrUpdateCourse_SUCCESS() throws Exception {
        mockMvc.perform(post("/courseSubmit")
                .param("submit", "submit")
                .param("title","title")
                .param("url","url"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getCourses"));
        verify(courseService,times(1)).saveCourse(any(CourseCommand.class));
    }

    @Test
    public void saveOrUpdateCourse_VALIDATION_FAIL() throws Exception {
        mockMvc.perform(post("/courseSubmit")
                .param("submit", "submit")
                .param("title","")
                .param("url","url"))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseForm"))
                .andExpect(model().attributeExists("taglist"))
                .andExpect(flash().attributeCount(0));
        verify(tagService,times(1)).findAll();
    }

    @Test
    public void addTagInAddCourse() throws Exception {
        mockMvc.perform(post("/courseSubmit")
                .param("addtag", "addtag"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/tagFormFromCourse"))
                .andExpect(flash().attributeExists("courseFormObject"))
                .andExpect(flash().attributeExists("createTagFromCourseForm"));
    }

    @Test
    public void cancelCourseForm() throws Exception {
        mockMvc.perform(post("/courseSubmit")
                .param("cancel", "cancel"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getCourses"));
    }

    @Test
    public void deleteCourse() throws Exception {
        mockMvc.perform(get("/course/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getCourses"));
    }

    @Test
    public void editCourse() throws Exception {
        mockMvc.perform(get("/course/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseForm"));
        verify(courseService,times(1)).getCourse(anyInt());
        verify(tagService,times(1)).findAll();
    }
}