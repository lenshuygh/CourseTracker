package com.lens.coursetracker.converter;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.model.Tag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TagToTagCommandTest {

    public static final Integer TAG_ID_VALUE = 1;
    public static final String TAG_NAME_VALUE = "testTagName";

    TagToTagCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new TagToTagCommand();
    }

    @Test
    public void convert() {
        Tag tag = new Tag();
        tag.setId(TAG_ID_VALUE);
        tag.setTagName(TAG_NAME_VALUE);

        TagCommand tagCommand = converter.convert(tag);

        assertEquals(TAG_ID_VALUE,tagCommand.getId());
        assertEquals(TAG_NAME_VALUE,tagCommand.getTagName());


    }
}