package com.lens.coursetracker.controller;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.service.TagService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
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

    TagController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new TagController(tagService,httpSession);
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
        TagCommand tagCommand = new TagCommand();
        tagCommand.setId(1);

        mockMvc.perform(post("/tagSubmit")
                .sessionAttr("CreateTagFromCourseForm",false)
                .param("cancel",""))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/getTags"));
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