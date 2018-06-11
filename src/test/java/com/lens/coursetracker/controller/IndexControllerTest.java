package com.lens.coursetracker.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    MockMvc mockMvc;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {
        indexController = new IndexController();
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void TestShowIndexNull() throws Exception {
        mockMvc.perform(get(""))
                .andExpect(view().name("index"));
    }

    @Test
    public void TestShowIndexSlash() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }
}