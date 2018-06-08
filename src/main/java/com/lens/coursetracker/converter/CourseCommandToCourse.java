package com.lens.coursetracker.converter;

import com.lens.coursetracker.command.CourseCommand;
import com.lens.coursetracker.model.Course;
import com.lens.coursetracker.model.Tag;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CourseCommandToCourse implements Converter<CourseCommand, Course> {
    @Nullable
    @Override
    public Course convert(CourseCommand source) {
        if(source == null){
            return null;
        }
        final Course course = new Course();
        course.setId(source.getId());
        course.setAuthor(source.getAuthor());
        course.setPublishdate(source.getPublishdate());
        course.setSubjects(source.getSubjects());
        course.setTitle(source.getTitle());
        course.setTags((Set<Tag>) source.getTags());
        course.setUrl(source.getUrl());
        return course;
    }
}
