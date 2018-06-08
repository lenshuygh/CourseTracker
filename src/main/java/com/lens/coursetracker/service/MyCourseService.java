package com.lens.coursetracker.service;

import com.lens.coursetracker.command.MyCourseCommand;
import com.lens.coursetracker.model.MyCourse;

import java.util.Set;

public interface MyCourseService {
    MyCourse getMyCourse(int id);

    Set<MyCourse> findAll();

    void save(MyCourse myCourse);

    void save(MyCourseCommand myCourseCommand);

    void deleteById(int id);

    MyCourseCommand getMyCourseCommand(int id);
}
