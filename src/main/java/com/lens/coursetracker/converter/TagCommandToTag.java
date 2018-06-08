package com.lens.coursetracker.converter;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.model.Tag;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TagCommandToTag  implements Converter<TagCommand,Tag> {

    @Nullable
    @Override
    public Tag convert(TagCommand source) {
        if(source == null){
            return null;
        }
        final Tag tag = new Tag();
        tag.setId(source.getId());
        tag.setTagName(source.getTagName());
        return tag;
    }
}
