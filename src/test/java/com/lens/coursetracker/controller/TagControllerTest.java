package com.lens.coursetracker.controller;

import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.converter.CourseCommandToCourse;
import com.lens.coursetracker.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TagControllerTest {

    @Mock
    TagService tagService;

    @Mock
    HttpSession httpSession;

    @Mock
    CourseCommandToCourse courseCommandToCourse;

    TagController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new TagController(tagService, httpSession, courseCommandToCourse);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getTags() throws Exception {
        mockMvc.perform(get("/getTags"))
                .andExpect(status().isOk())
                .andExpect(view().name("tag/tagOverview"))
                .andExpect(model().attributeExists("tags"));
        verify(tagService, times(1)).findAll();
    }

    @Test
    public void showTagFormFromCourse() throws Exception {
        mockMvc.perform(get("/tagFormFromCourse")
                .param("courseFormObject", String.valueOf(new CourseCommand()))
                .param("createTagFromCourseForm", "true"))
                .andExpect(status().isOk())
                .andExpect(view().name("tag/tagForm"))
                .andExpect(model().attributeExists("tag"));
    }

    @Test
    public void showTagForm() throws Exception {
        mockMvc.perform(get("/tagForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("tag/tagForm"))
                .andExpect(model().attributeExists("tag"));
    }

    @Test
    public void saveOrUpdateTag_VALIDATION_SUCCESS() throws Exception {
        mockMvc.perform(post("/tagSubmit")
                .param("tagName","tagName"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getTags"));
        verify(tagService,times(1)).save(any(TagCommand.class));
    }

    @Test
    public void saveOrUpdateTag_VALIDATION_ERROR() throws Exception{
        mockMvc.perform(post("/tagSubmit")
                .param("tagName",""))
                .andExpect(status().isOk())
                .andExpect(view().name("tag/tagForm"));
    }

    @Test
    public void deleteTag() throws Exception {
        mockMvc.perform(get("/tag/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getTags"));
        verify(tagService,times(1)).deleteById(anyInt());
    }

    @Test
    public void cancelTagForm() throws Exception {
        mockMvc.perform(post("/tagSubmit")
                .param("cancel", "cancel"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getTags"));
    }

    @Test
    public void editTag() throws Exception {
        mockMvc.perform(get("/tag/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tag/tagForm"));
        verify(tagService,times(1)).getTag(anyInt());
    }

}