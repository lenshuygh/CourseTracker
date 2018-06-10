package com.lens.coursetracker.service;

import com.lens.coursetracker.CoursetrackerApplication;
import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.converter.TagCommandToTag;
import com.lens.coursetracker.converter.TagToTagCommand;
import com.lens.coursetracker.model.Tag;
import com.lens.coursetracker.repository.TagRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagCommandToTag tagCommandToTag;
    private final TagToTagCommand tagToTagCommand;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagCommandToTag tagCommandToTag, TagToTagCommand tagToTagCommand) {
        this.tagRepository = tagRepository;
        this.tagCommandToTag = tagCommandToTag;
        this.tagToTagCommand = tagToTagCommand;
    }

    private static Logger logger = LogManager.getLogger(CoursetrackerApplication.class);

    @Override
    public void save(TagCommand tagCommand) {
        Tag tagToSave = tagCommandToTag.convert(tagCommand);
        logger.info("save() -> " + tagCommand);
        try {
            tagRepository.save(tagToSave);
        } catch (DataIntegrityViolationException d) {
            logger.error("save() -> " + tagCommand + " ALREADY PRESENT");
            throw new DataIntegrityViolationException("TagName already present: " + tagToSave.getTagName());
        }
    }

    @Override
    public TagCommand getTag(int id) {
        logger.info("getTag() -> " + id);
        return tagToTagCommand.convert(tagRepository.getOne(id));
    }

    @Override
    public Set<TagCommand> findAll() {
        logger.info("findAll()");
        return StreamSupport.stream(tagRepository.findAll().spliterator(), false).map(tagToTagCommand::convert).collect(Collectors.toSet());
    }

    @Override
    public void deleteById(Integer id) {
        logger.info("deleteById() -> " + id);
        try {
            tagRepository.deleteById(id);
        } catch (DataIntegrityViolationException d) {
            logger.error("deleteById() -> " + id + " ALREADY ASSIGNED");
            throw new DataIntegrityViolationException("Tag '" + getTag(id).getTagName() + "'is assigned to courses, it cannot be deleted");
        }

    }
}
