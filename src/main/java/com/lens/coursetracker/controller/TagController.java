package com.lens.coursetracker.controller;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class TagController {
    private final TagService tagService;
    private final HttpSession session;

    @Autowired
    public TagController(TagService tagService, HttpSession session) {
        this.tagService = tagService;
        this.session = session;
    }

    @GetMapping("getTags")
    public String getTags(Model model) {
        model.addAttribute("tags", tagService.findAll());
        return "tag/tagOverview";
    }

    @GetMapping("tagForm")
    public String showTagForm(Model model) {
        model.addAttribute("tag", new TagCommand());
        return "tag/tagForm";
    }

    @PostMapping("tagSubmit")
    public String saveOrUpdateTag(@Valid @ModelAttribute("tag") TagCommand tagCommand, @SessionAttribute("CreateTagFromCourseForm") boolean createTagFromCourseForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tag/tagForm";
        }
        tagService.save(tagCommand);
        if (createTagFromCourseForm) {
            return "redirect:/courseForm";
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
    public String cancelCourseForm() {
        return "redirect:/getTags";
    }

    @GetMapping("/tag/edit/{id}")
    public String editTag(@PathVariable String id, Model model) {
        model.addAttribute("tag", tagService.getTag(Integer.valueOf(id)));
        return "tag/tagForm";
    }

}
