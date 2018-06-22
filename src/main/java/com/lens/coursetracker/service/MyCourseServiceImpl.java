package com.lens.coursetracker.service;

import com.lens.coursetracker.CoursetrackerApplication;
import com.lens.coursetracker.command.MyCourseCommand;
import com.lens.coursetracker.model.MyCourse;
import com.lens.coursetracker.repository.CourseRepository;
import com.lens.coursetracker.repository.MyCourseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
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

    private static Logger logger = LogManager.getLogger(CoursetrackerApplication.class);

    @Override
    public MyCourse getMyCourse(int id) {
        logger.info("getMyCourse() -> " + id);
        Optional<MyCourse> theMyCourse = myCourseRepository.findById(id);
        if(!theMyCourse.isPresent()){
            throw new EntityNotFoundException("MyCourse not found for id: " + id);
        }
        return theMyCourse.get();
    }

    @Override
    public Set<MyCourse> findAll() {
        logger.info("findAll()");
        Set<MyCourse> myCourseSet = new HashSet<>();
        myCourseRepository.findAll().iterator().forEachRemaining(myCourseSet::add);
        return myCourseSet;
    }

    @Override
    public void save(MyCourse myCourse) {
        logger.info("save() -> "+ myCourse);
        myCourseRepository.save(myCourse);
    }

    @Override
    public void save(MyCourseCommand myCourseCommand) {
        MyCourse myCourse = new MyCourse();
        myCourse.setId(myCourseCommand.getId());
        myCourse.setCompleted(myCourseCommand.getCompleted());
        myCourse.setNotes(myCourseCommand.getNotes());
        myCourse.setCourse(courseRepository.getOne(myCourseCommand.getCourse()));
        logger.info("save() -> " + myCourse);
        save(myCourse);
    }

    @Override
    public void deleteById(int id) {
        logger.info("deleteById() -> " + id);
        if(!myCourseRepository.findById(id).isPresent()){
            throw new EntityNotFoundException("MyCourse not found for id: "+id);
        }
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
        logger.info("getMyCourseCommand() -> " + id);
        return myCourseCommand;

    }
}
