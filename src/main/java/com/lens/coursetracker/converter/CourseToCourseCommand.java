package com.lens.coursetracker.converter;

import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.model.Course;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseCommand implements Converter<Course,CourseCommand> {
    @Nullable
    @Override
    public CourseCommand convert(Course source) {
        if(source == null){
            return null;
        }
        final CourseCommand courseCommand = new CourseCommand();
        courseCommand.setId(source.getId());
        courseCommand.setAuthor(source.getAuthor());
        courseCommand.setPublishdate(source.getPublishdate());
        courseCommand.setSubjects(source.getSubjects());
        //courseCommand.setTags(sourse.getTags());
        courseCommand.setTitle(source.getTitle());
        courseCommand.setUrl(source.getUrl());
        return courseCommand;
    }
}
