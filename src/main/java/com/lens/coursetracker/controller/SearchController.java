package com.lens.coursetracker.controller;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.model.MyCourse;
import com.lens.coursetracker.service.CourseService;
import com.lens.coursetracker.service.MyCourseService;
import com.lens.coursetracker.service.SearchService;
import com.lens.coursetracker.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
public class SearchController {

    private final TagService tagService;
    private final CourseService courseService;
    private final MyCourseService myCourseService;
    private final SearchService searchService;

    @Autowired
    public SearchController(TagService tagService, CourseService courseService, MyCourseService myCourseService,SearchService searchService) {
        this.tagService = tagService;
        this.courseService = courseService;
        this.myCourseService = myCourseService;
        this.searchService = searchService;
    }

    @GetMapping("searchTag")
    public String tagSearch(Model model){
        model.addAttribute("availableTags",tagService.findAll());
        model.addAttribute("SearchTag",new TagCommand());
        return "search/tagSearch";
    }

    @GetMapping("searchAll")
    public String tagAll(){
        return "/search/allSearch";
    }


    @PostMapping("tagSearchSubmit")
    public ModelAndView tagSearchSubmit(@ModelAttribute TagCommand tagCommand, Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("availableTags",tagService.findAll());
        model.addAttribute("SearchTag",new TagCommand());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/searchTag");
        redirectAttributes.addFlashAttribute("courses",searchService.searchCoursesByTag(tagCommand.getId()));
        redirectAttributes.addFlashAttribute("myCourses",searchService.searchMyCoursesByTag(tagCommand.getId()));
        return modelAndView;
    }
}
