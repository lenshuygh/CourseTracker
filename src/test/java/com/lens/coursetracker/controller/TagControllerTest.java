package com.lens.coursetracker.controller;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.converter.CourseCommandToCourse;
import com.lens.coursetracker.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
        controller = new TagController(tagService,httpSession,courseCommandToCourse);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getTags() throws Exception {
        mockMvc.perform(get("/getTags"))
                .andExpect(view().name("tag/tagOverview"))
                .andExpect(model().attributeExists("tags"));
    }

    @Test
    public void showTagForm() throws Exception{
        mockMvc.perform(get("/tagForm"))
                .andExpect(view().name("tag/tagForm"))
                .andExpect(model().attributeExists("tag"));
    }

    @Test
    public void saveOrUpdateTag() throws Exception{
        when(httpSession.getAttribute("CreateTagFromCourseForm")).thenReturn(true);

        mockMvc.perform(post("/tagSubmit")
                .param("cancel","")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getTags"));


    }

    @Test
    public void saveOrUpdateTagValidationFailed() throws Exception{
        //HashMap<String,Object> sessionAttributes = new HashMap<>();
        //sessionAttributes.put("createTagFromCourseForm",false);
        boolean createTagFromCourseForm = true;

        mockMvc.perform(post("/tagSubmit")
                //.sessionAttrs(sessionAttributes)
                        //.sessionAttr("CreateTagFromCourseForm",true)
                //.contentType(MediaType.APPLICATION_FORM_URLENCODED)
                //.param("tagName","")
                .param("cancel","cancel")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getTags"))
                .andDo(print());
    }

    @Test
    public void deleteTag() throws Exception{
        mockMvc.perform(get("/tag/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getTags"));
    }

    @Test
    public void cancelTagForm() throws Exception{
        mockMvc.perform(post("/tagSubmit")
                .param("cancel","cancel"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getTags"));


    }

    @Test
    public void editTag() throws Exception{
        mockMvc.perform(get("/tag/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tag/tagForm"));
    }
}