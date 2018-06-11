package com.lens.coursetracker.converter;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.model.Tag;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TagCommandToTagTest {

    public static final Integer TAG_ID_VALUE = 1;
    public static final String TAG_NAME_VALUE = "testTagName";

    TagCommandToTag converter;

    @Before
    public void setUp() throws Exception {
        converter = new TagCommandToTag();
    }

    @Test
    public void convert() {
        TagCommand tagCommand = new TagCommand();
        tagCommand.setId(TAG_ID_VALUE);
        tagCommand.setTagName(TAG_NAME_VALUE);

        Tag tag = converter.convert(tagCommand);

        assertEquals(TAG_ID_VALUE,tag.getId());
        assertEquals(TAG_NAME_VALUE,tag.getTagName());
    }
}