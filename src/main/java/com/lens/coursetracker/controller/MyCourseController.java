package com.lens.coursetracker.controller;

import com.lens.coursetracker.command.MyCourseCommand;
import com.lens.coursetracker.model.MyCourse;
import com.lens.coursetracker.service.CourseService;
import com.lens.coursetracker.service.MyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MyCourseController {

    private final MyCourseService myCourseService;
    private final CourseService courseService;
    private final HttpSession session;

    @Autowired
    public MyCourseController(MyCourseService myCourseService, CourseService courseService,HttpSession session) {
        this.myCourseService = myCourseService;
        this.courseService = courseService;
        this.session = session;
    }

    @GetMapping("getMyCourses")
    public String getMyCourses(Model model){
        model.addAttribute("myCourses",myCourseService.findAll());
        return "myCourse/myCourseOverview";
    }

    @GetMapping("myCourseForm")
    public String showMyCourseForm(Model model){
        model.addAttribute("myCourse",new MyCourse());
        model.addAttribute("availableCourses",courseService.findAll());
        return "myCourse/myCourseForm";
    }

    @PostMapping("myCourseSubmit")
    public String saveOrUpdateMyCourse(@ModelAttribute("tag") MyCourseCommand myCourse){
        myCourseService.save(myCourse);
        return "redirect:/getMyCourses";
    }

    @GetMapping("/myCourse/delete/{id}")
    public String deleteTag(@PathVariable String id){
        myCourseService.deleteById(Integer.valueOf(id));
        return "redirect:/getMyCourses";
    }

    @GetMapping("myCourse/edit/{id}")
    private String editCourse(@PathVariable String id,Model model){
        model.addAttribute("myCourse",myCourseService.getMyCourseCommand (Integer.valueOf(id)));
        model.addAttribute("availableCourses",courseService.findAll());
        return "myCourse/myCourseForm";
    }

    @PostMapping(value = "myCourseSubmit",params = "cancel")
    public String cancelMyCourseForm(){
        return "redirect:/getMyCourses";
    }
}
