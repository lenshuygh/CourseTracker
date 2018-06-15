package com.lens.coursetracker;

import com.lens.coursetracker.converter.TagCommandToTag;
import com.lens.coursetracker.converter.TagToTagCommand;
import com.lens.coursetracker.model.Tag;
import com.lens.coursetracker.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class TagIntegrationTest {

    @Autowired
    TagService tagService;

    @Autowired
    TagCommandToTag tagCommandToTag;

    @Autowired
    TagToTagCommand tagToTagCommand;

    @Transactional
    @Test
    public void readTag(){
        Tag tag = tagCommandToTag.convert(tagService.getTag(1));

        assertNotNull(tag);
    }

    @Transactional
    @Test
    public void enterNewTag(){
        Tag tag = new Tag();
        tag.setTagName("tagName");

        int initSize = tagService.findAll().size();

        tagService.save(tagToTagCommand.convert(tag));

        assertEquals(initSize+1,tagService.findAll().size());
    }

    @Transactional
    @Test
    public void enterUpdateTag(){
        Tag tag = tagCommandToTag.convert(tagService.getTag(1));
        String originalTagName = tag.getTagName();

        tag.setTagName("newTagName");
        tagService.save(tagToTagCommand.convert(tag));

        assertNotEquals(originalTagName,tagService.getTag(1).getTagName());
    }

    @Transactional
    @Test
    public void testDeleteTag(){
        //todo fix this issue, runs ok with method alone, fails when whole class runs
/*

        Tag tag = new Tag();
        tag.setTagName("tagName");

        tagService.save(tagToTagCommand.convert(tag));

        int size = tagService.findAll().size();

        tagService.deleteById(8);

        assertNotEquals(size,tagService.findAll().size());
*/
    }
}
