package com.lens.coursetracker.controller;

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
        controller = new CourseController(courseService,tagService,myCourseService,session,courseCommandToCourse);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


    }

    @Test
    public void TestGetCourses() throws Exception{
        mockMvc.perform(get("/getCourses"))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseOverview"))
                .andExpect(model().attributeExists("courses"));
    }

    @Test
    public void TestShowFormStandard() throws Exception{
        mockMvc.perform(get("/courseForm")
                .sessionAttr("CreateTagFromCourseForm",false))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseForm"))
                .andExpect(model().attributeExists("taglist"))
                .andExpect(model().attributeExists("course"))
                .andDo(print());

    }

    @Test
    public void TestShowFormAfterTagAdded() throws Exception{
        mockMvc.perform(get("/courseForm")
                .sessionAttr("CreateTagFromCourseForm",true))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseForm"))
                .andDo(print())
                .andExpect(model().attributeExists("taglist"))
                .andDo(print());
    }

    @Test
    public void TestSaveOrUpdateCourse() throws Exception{
        mockMvc.perform(post("/courseSubmit")
                .param("submit","submit").
                        contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseForm"))
                .andExpect(model().attributeExists("taglist"));
    }

    @Test
    public void TestAddTagInAddCourse() throws Exception {
        mockMvc.perform(post("/courseSubmit")
                .param("addtag","addtag"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/tagForm"));
    }

    @Test
    public void TestCancelCourseForm() throws Exception {
        mockMvc.perform(post("/courseSubmit")
        .param("cancel","cancel"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getCourses"));
    }

    @Test
    public void TestDeleteCourse() throws Exception{
        mockMvc.perform(get("/course/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getCourses"));
    }

    @Test
    public void TestEditCourse() throws Exception{
        mockMvc.perform(get("/course/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("course/courseForm"));
    }
}