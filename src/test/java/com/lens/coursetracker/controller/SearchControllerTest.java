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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SearchControllerTest {

    @Mock
    TagService tagService;

    @Mock
    CourseService courseService;

    @Mock
    MyCourseService myCourseService;

    @Mock
    SearchService searchService;

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
                .andExpect(status().isOk())
                .andExpect(view().name("search/tagSearch"))
                .andExpect(model().attributeExists("availableTags"))
                .andExpect(model().attributeExists("SearchTag"));
        verify(tagService,times(1)).findAll();
    }

    @Test
    public void tagAll() throws Exception{
        mockMvc.perform(get("/searchAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("/search/allSearch"));
    }

    @Test
    public void tagSearchSubmit() throws Exception{

        // todo problem = flashAttributes on RedirectAttributes ?
        /*Course course = new Course();
        course.setId(1);
        Set<Course> courses = new HashSet<>();
        courses.add(course);
        MyCourse myCourse = new MyCourse();
        myCourse.setId(1);
        Set<MyCourse> myCourses = new HashSet<>();
        myCourses.add(myCourse);

        when(searchService.searchMyCoursesByTag(anyInt())).thenReturn(myCourses);
        when(searchService.searchCoursesByTag(anyInt())).thenReturn(courses);

        Map<String,Object> flashMap = new HashMap<>();
        flashMap.put("courses",courses);
        flashMap.put("myCourses",myCourses);

        mockMvc.perform(post("/tagSearchSubmit")
                //.flashAttr("courses",redirectAttributes.addAttribute(courses))
                //.flashAttr("myCourses",redirectAttributes.addAttribute(myCourses))
                .flashAttrs(flashMap)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/searchTag"))
                .andExpect(model().attributeExists("availableTags"))
                .andExpect(model().attributeExists("SearchTag"))
                .andExpect(flash().attributeExists("courses"))
                .andExpect(flash().attributeExists("myCourses"))
        ;
    */

    }
}