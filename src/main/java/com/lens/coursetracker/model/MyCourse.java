package com.lens.coursetracker.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class MyCourse implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Course course;
    private String notes;
    private Boolean completed;

    public MyCourse() {
    }

    public MyCourse(Course course, String notes, Boolean completed) {
        this.course = course;
        this.notes = notes;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", course=" + course +
                ", notes='" + notes + '\'' +
                ", completed=" + completed +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.course.getTitle().compareTo(((MyCourse)o).course.getTitle());
    }
}
