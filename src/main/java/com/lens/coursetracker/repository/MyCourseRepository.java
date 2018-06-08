package com.lens.coursetracker.repository;

import com.lens.coursetracker.model.MyCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCourseRepository extends JpaRepository<MyCourse,Integer> {
}
