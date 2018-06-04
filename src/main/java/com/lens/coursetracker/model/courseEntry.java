package com.lens.coursetracker.model;

public class courseEntry {
    private Integer id;
    private Integer userId;
    private Integer courseId;
    private String notes;
    private String tags;
    private Boolean completed;

    public courseEntry(Integer id, Integer userId, Integer courseId, String notes, String tags, Boolean completed) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.notes = notes;
        this.tags = tags;
        this.completed = completed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "course{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", notes='" + notes + '\'' +
                ", tags='" + tags + '\'' +
                ", completed=" + completed +
                '}';
    }
}
