package com.lens.coursetracker.service;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.converter.TagCommandToTag;
import com.lens.coursetracker.converter.TagToTagCommand;
import com.lens.coursetracker.model.Tag;
import com.lens.coursetracker.repository.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TagServiceImplTest {

    @Mock
    TagRepository tagRepository;

    @Mock
    TagCommandToTag tagCommandToTag;

    @Mock
    TagToTagCommand tagToTagCommand;

    TagServiceImpl tagService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tagService = new TagServiceImpl(tagRepository,tagCommandToTag,tagToTagCommand);
    }

    @Test
    public void getTag() {
        Tag tag = new Tag();
        tag.setTagName("testTag");
        tag.setId(1);

        TagCommand tagCommand = new TagCommand();
        tagCommand.setTagName("testTag");
        tagCommand.setId(1);

        when(tagRepository.getOne(anyInt())).thenReturn(tag);
        when(tagToTagCommand.convert(tag)).thenReturn(tagCommand);

        TagCommand foundTagCommand = tagService.getTag(1);

        assertEquals(tagCommand.getTagName(),foundTagCommand.getTagName());
        verify(tagRepository,times(1)).getOne(anyInt());
        verify(tagToTagCommand,times(1)).convert(tag);
    }

    @Test
    public void findAll() {
        Tag tag = new Tag();
        tag.setTagName("testTag");
        tag.setId(1);

        List<Tag> tags = new ArrayList<>();

        tags.add(tag);

        TagCommand tagCommand = new TagCommand();
        tagCommand.setTagName(tag.getTagName());
        tagCommand.setId(tag.getId());

        Set<TagCommand> tagCommands = new HashSet<>();
        tagCommands.add(tagCommand);

        when(tagRepository.findAll()).thenReturn(tags);
        when(tagToTagCommand.convert(any())).thenReturn(tagCommand);

        Set<TagCommand> resultTagCommands;
        resultTagCommands = tagService.findAll();

        assertEquals(tagCommands.size(),resultTagCommands.size());
    }
}