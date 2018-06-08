package com.lens.coursetracker.command;

public class MyCourseCommand {
    private Integer id;
    private Integer course;
    private String notes;
    private Boolean completed;

    public MyCourseCommand() {
    }

    public MyCourseCommand(Integer id, int course, String notes, Boolean completed) {
        this.id = id;
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

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
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
        return "MyCourseCommand{" +
                "id=" + id +
                ", course=" + course +
                ", notes='" + notes + '\'' +
                ", completed=" + completed +
                '}';
    }
}
