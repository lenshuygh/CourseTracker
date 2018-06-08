package com.lens.coursetracker.service;

import com.lens.coursetracker.command.MyCourseCommand;
import com.lens.coursetracker.model.MyCourse;
import com.lens.coursetracker.repository.CourseRepository;
import com.lens.coursetracker.repository.MyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyCourseServiceImpl implements MyCourseService {
    private final MyCourseRepository myCourseRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public MyCourseServiceImpl(MyCourseRepository myCourseRepository, CourseRepository courseRepository) {
        this.myCourseRepository = myCourseRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public MyCourse getMyCourse(int id) {
        return myCourseRepository.getOne(id);
    }

    @Override
    public Set<MyCourse> findAll() {
        Set<MyCourse> myCourseSet = new HashSet<>();
        myCourseRepository.findAll().iterator().forEachRemaining(myCourseSet::add);
        return myCourseSet;
    }

    @Override
    public void save(MyCourse myCourse) {
        myCourseRepository.save(myCourse);
    }

    @Override
    public void save(MyCourseCommand myCourseCommand) {
        MyCourse myCourse = new MyCourse();
        myCourse.setId(myCourseCommand.getId());
        myCourse.setCompleted(myCourseCommand.getCompleted());
        myCourse.setNotes(myCourseCommand.getNotes());
        myCourse.setCourse(courseRepository.getOne(myCourseCommand.getCourse()));
        save(myCourse);
    }

    @Override
    public void deleteById(int id) {
        myCourseRepository.deleteById(id);
    }

    @Override
    public MyCourseCommand getMyCourseCommand(int id) {
        MyCourse myCourse = getMyCourse(id);
        MyCourseCommand myCourseCommand = new MyCourseCommand();
        myCourseCommand.setId(myCourse.getId());
        myCourseCommand.setCompleted(myCourse.getCompleted());
        myCourseCommand.setNotes(myCourse.getNotes());
        myCourseCommand.setCourse(myCourse.getCourse().getId());
        return myCourseCommand;

    }
}
