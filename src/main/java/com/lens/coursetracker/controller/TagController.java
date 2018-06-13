package com.lens.coursetracker.controller;

import com.lens.coursetracker.CoursetrackerApplication;
import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.converter.CourseCommandToCourse;
import com.lens.coursetracker.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class TagController {
    private final TagService tagService;
    private final HttpSession session;
    private final CourseCommandToCourse courseCommandToCourse;
    private CourseCommand formCourseCommand;
    private boolean backToCourse;

    @Autowired
    public TagController(TagService tagService, HttpSession session,CourseCommandToCourse courseCommandToCourse) {
        this.tagService = tagService;
        this.session = session;
        this.courseCommandToCourse = courseCommandToCourse;
    }

    private static Logger logger = LogManager.getLogger(CoursetrackerApplication.class);

    @GetMapping("getTags")
    public String getTags(Model model) {
        model.addAttribute("tags", tagService.findAll());
        return "tag/tagOverview";
    }

    @GetMapping("tagFormFromCourse")
    public String showTagFormFromCourse(Model model, @ModelAttribute("courseFormObject")CourseCommand courseCommand,@ModelAttribute("createTagFromCourseForm") boolean createTagFromCourseForm) {
        formCourseCommand = courseCommand;
        backToCourse = createTagFromCourseForm;
        model.addAttribute("tag", new TagCommand());
        return "tag/tagForm";
    }

    @GetMapping("tagForm")
    public String showTagForm(Model model) {
        model.addAttribute("tag", new TagCommand());
        return "tag/tagForm";
    }

    @PostMapping("tagSubmit")
    public String saveOrUpdateTag(@Valid @ModelAttribute("tag") TagCommand tagCommand, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "tag/tagForm";
        }
        tagService.save(tagCommand);
        if (backToCourse) {
            model.addAttribute("course",courseCommandToCourse.convert(formCourseCommand));
            model.addAttribute("taglist",tagService.findAll());
            return "course/courseForm";
        } else {
            return "redirect:/getTags";
        }
    }

    @GetMapping("/tag/delete/{id}")
    public String deleteTag(@PathVariable String id) {
        tagService.deleteById(Integer.valueOf(id));
        return "redirect:/getTags";
    }

    @PostMapping(value = "tagSubmit", params = "cancel")
    public String cancelTagForm(Model model) {
        if (backToCourse) {
            model.addAttribute("course",courseCommandToCourse.convert(formCourseCommand));
            model.addAttribute("taglist",tagService.findAll());
            return "course/courseForm";
        }else{
            return "redirect:/getTags";
        }

    }

    @GetMapping("/tag/edit/{id}")
    public String editTag(@PathVariable String id, Model model) {
        model.addAttribute("tag", tagService.getTag(Integer.valueOf(id)));
        return "tag/tagForm";
    }

}
