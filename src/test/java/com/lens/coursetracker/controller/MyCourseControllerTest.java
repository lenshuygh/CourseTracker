package com.lens.coursetracker.controller;

import com.lens.coursetracker.command.MyCourseCommand;
import com.lens.coursetracker.service.CourseService;
import com.lens.coursetracker.service.MyCourseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class MyCourseControllerTest {

    @Mock
    MyCourseService myCourseService;

    @Mock
    CourseService courseService;

    MyCourseController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new MyCourseController(myCourseService,courseService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getMyCourses() throws Exception{
        mockMvc.perform(get("/getMyCourses"))
                .andExpect(view().name("myCourse/myCourseOverview"))
                .andExpect(model().attributeExists("myCourses"));
    }

    @Test
    public void showMyCourseForm() throws Exception {
        mockMvc.perform(get("/myCourseForm"))
                .andExpect(view().name("myCourse/myCourseForm"))
                .andExpect(model().attributeExists("myCourse"))
                .andExpect(model().attributeExists("availableCourses"));
    }

    @Test
    public void saveOrUpdateMyCourse() throws Exception{
        mockMvc.perform(post("/myCourseSubmit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getMyCourses"));

    }

    @Test
    public void deleteMyCourse() throws Exception{
        mockMvc.perform(get("/myCourse/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getMyCourses"));
    }

    @Test
    public void cancelMyCourseForm() throws Exception{
        mockMvc.perform(post("/myCourseSubmit")
                .param("cancel","cancel"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getMyCourses"));
    }

    @Test
    public void editCourse() throws Exception{
        when(myCourseService.getMyCourseCommand(anyInt())).thenReturn(new MyCourseCommand());

        mockMvc.perform(get("/myCourse/edit/1"))
                .andExpect(view().name("myCourse/myCourseForm"))
                .andDo(print())
                .andExpect(model().attributeExists("myCourse"))
                .andExpect(model().attributeExists("availableCourses"));
    }


}