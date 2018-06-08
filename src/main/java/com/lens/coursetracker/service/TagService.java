package com.lens.coursetracker.service;

import com.lens.coursetracker.command.TagCommand;

import java.util.Set;

public interface TagService {
    void save(TagCommand tagCommand);

    TagCommand getTag(int id);

    Set<TagCommand> findAll();

    void deleteById(Integer id);
}
