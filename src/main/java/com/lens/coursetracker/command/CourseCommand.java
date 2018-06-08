package com.lens.coursetracker.command;

import com.lens.coursetracker.model.Tag;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

public class CourseCommand {
    private Integer id;
    @NotEmpty
    private String title;
    private String author;
    @NotEmpty
    private String url;
    private String subjects;
    private Date publishdate;
    private Set<Tag> tags;


    public CourseCommand() {
    }

    public CourseCommand(String title, String author, String url, String subjects, Date publishdate, Set<Tag> tags/*String[] tags*/) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.subjects = subjects;
        this.publishdate = publishdate;
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "CourseCommand{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", subjects='" + subjects + '\'' +
                ", publishdate=" + publishdate +
                ", tags=" + tags +
                '}';
    }
}
