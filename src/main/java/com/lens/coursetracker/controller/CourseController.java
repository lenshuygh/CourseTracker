package com.lens.coursetracker.controller;

import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.converter.CourseCommandToCourse;
import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.service.MyCourseService;
import com.lens.coursetracker.service.CourseService;
import com.lens.coursetracker.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CourseController {

    private final CourseService courseService;
    private final TagService tagService;
    private final MyCourseService MyCourseService;
    private final HttpSession session;
    private final CourseCommandToCourse courseCommandToCourse;

    @Autowired
    public CourseController(CourseService courseService, TagService tagService, MyCourseService MyCourseService, HttpSession session, CourseCommandToCourse courseCommandToCourse) {
        this.courseService = courseService;
        this.tagService = tagService;
        this.MyCourseService = MyCourseService;
        this.session = session;
        this.courseCommandToCourse = courseCommandToCourse;
    }


    @GetMapping("/getCourses")
    public String getCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "course/courseOverview";
    }

    @GetMapping("/courseForm")
    public String showForm(Model model) {
        if(null == session.getAttribute("CreateTagFromCourseForm") || (!(boolean) session.getAttribute("CreateTagFromCourseForm"))){
            model.addAttribute("course", new Course());

        }else {
            session.setAttribute("CreateTagFromCourseForm",false);
            model.addAttribute("course", courseCommandToCourse.convert((CourseCommand)session.getAttribute("courseCommand") ));
        }
        model.addAttribute("taglist", tagService.findAll());
        return "course/courseForm";
    }

    @PostMapping(value = "courseSubmit",params = "submit")
    public ModelAndView saveOrUpdateCourse(@Valid @ModelAttribute("course") CourseCommand courseCommand, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("course/courseForm");
            modelAndView.addObject("taglist", tagService.findAll());
            return modelAndView;
        }
        courseService.saveCourse(courseCommand);
        modelAndView.setViewName("redirect:/getCourses");
        return modelAndView;
    }

    @PostMapping(value = "courseSubmit",params = "addtag")
    public String addTagInAddCourse(@ModelAttribute("course") CourseCommand courseCommand) {
        session.setAttribute("CreateTagFromCourseForm",true);
        session.setAttribute("courseCommand",courseCommand);
        return "redirect:/tagForm";
    }

    @PostMapping(value = "courseSubmit",params = "cancel")
    public String cancelCourseForm(){
        return "redirect:/getCourses";
    }

    @ModelAttribute("dateFormat")
    public String dateFormat() {
        return "yyyy-MM-dd";
    }

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormat());
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }

    @GetMapping("course/delete/{id}")
    private String deleteCourse(@PathVariable String id){
        courseService.deleteById(Integer.valueOf(id));
        return "redirect:/getCourses";
    }

    @GetMapping("course/edit/{id}")
    private String editCourse(@PathVariable String id,Model model){
        model.addAttribute("course",courseService.getCourse (Integer.valueOf(id)));
        model.addAttribute("taglist",tagService.findAll());
        return "course/courseForm";
    }

}
