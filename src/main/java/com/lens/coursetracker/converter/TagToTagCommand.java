package com.lens.coursetracker.converter;

import com.lens.coursetracker.command.TagCommand;
import com.lens.coursetracker.model.Tag;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TagToTagCommand implements Converter<Tag,TagCommand> {

    @Nullable
    @Override
    public TagCommand convert(Tag source) {
        if(source == null){
            return null;
        }
        final TagCommand tagCommand = new TagCommand();
        tagCommand.setId(source.getId());
        tagCommand.setTagName(source.getTagName());
        return tagCommand;
    }
}
