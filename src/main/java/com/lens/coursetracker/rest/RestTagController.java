package com.lens.coursetracker.rest;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class RestTagController {

    @Autowired
    private final TagService tagService;

    public RestTagController(TagService tagService) {
        this.tagService = tagService;
    }


    @GetMapping("/tags")
    public Collection<TagCommand> getTags(){
        return tagService.findAll();
    }

    @GetMapping("/tag/{id}")
    public TagCommand getTag(@PathVariable int id){
        return tagService.getTag(id);
    }

    @PostMapping("/tag/")
    Collection<TagCommand> addTag(@RequestBody TagCommand tagCommand){
        tagService.save(tagCommand);
        return tagService.findAll();
    }

    @PostMapping("tag/update/{id}")
    Collection<TagCommand> updateTag(@PathVariable int id,@RequestBody TagCommand tagCommand){
        TagCommand tagCommandtoUpdate = tagService.getTag(id);
        tagCommandtoUpdate.setTagName(tagCommand.getTagName());
        tagService.save(tagCommandtoUpdate);
        return tagService.findAll();
    }

    @GetMapping("tag/delete/{id}")
    Collection<TagCommand> deleteTag(@PathVariable int id){
        tagService.deleteById(id);
        return tagService.findAll();
    }
}
