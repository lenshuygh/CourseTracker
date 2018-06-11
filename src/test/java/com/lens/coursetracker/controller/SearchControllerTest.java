package com.lens.coursetracker.controller;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.model.MyCourse;
import com.lens.coursetracker.service.CourseService;
import com.lens.coursetracker.service.MyCourseService;
import com.lens.coursetracker.service.SearchService;
import com.lens.coursetracker.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class SearchControllerTest {

    @Mock
    TagService tagService;

    @Mock
    CourseService courseService;

    @Mock
    MyCourseService myCourseService;

    @Mock
    SearchService searchService;

    @Mock
    RedirectAttributes redirectAttributes;

    @Mock
    TagCommand tagCommand;

    MockMvc mockMvc;

    SearchController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new SearchController(tagService,courseService,myCourseService,searchService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void tagSearch() throws Exception {
        mockMvc.perform(get("/searchTag"))
                .andExpect(view().name("search/tagSearch"))
                .andExpect(model().attributeExists("availableTags"))
                .andExpect(model().attributeExists("SearchTag"));
    }

    @Test
    public void tagAll() throws Exception{
        mockMvc.perform(get("/searchAll"))
                .andExpect(view().name("/search/allSearch"));
    }

    @Test
    public void tagSearchSubmit() throws Exception{
        //todo: fix this test

       /* Map<String,Object> flashAttMap = new HashMap<>();
        Set<Course> courses = new HashSet<>();
        Set<MyCourse> myCourses = new HashSet<>();
        Course c1 = new Course();
        Course c2 = new Course();
        c1.setId(1);
        c2.setId(2);
        c1.setTitle("title");
        c2.setTitle("title");
        c1.setUrl("url");
        c2.setUrl("url");
        courses.add(c1);
        courses.add(c2);
        //flashAttMap.put("courses",courses);
        //flashAttMap.put("myCourses",myCourses);

        TagCommand tagCommand = new TagCommand();

        when(tagCommand.getId(anyInt())).thenReturn()

        when(searchService.searchCoursesByTag(anyInt())).thenReturn(courses);
        when(searchService.searchMyCoursesByTag(anyInt())).thenReturn(myCourses);

        redirectAttributes.addFlashAttribute("courses",courses);
        redirectAttributes.addFlashAttribute("myCourses",myCourses);

        mockMvc.perform(post("/tagSearchSubmit"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("availableTags"))
                .andExpect(model().attributeExists("SearchTag"))
                .andExpect(view().name("redirect:/searchTag"));
        */
    }
}