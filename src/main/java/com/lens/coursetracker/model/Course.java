package com.lens.coursetracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // to prevent error on lazy loading of the Tags that causes it because Jackson started mapping without load being complete
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Course implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String title;
    private String author;
    @NotEmpty
    private String url;
    private String subjects;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date publishdate;
    @ManyToMany
    private Set<Tag> tags;

    public Course() {
    }

    public Course(String title, String author, String url, String subjects, Date publishdate, Set<Tag> tags) {
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
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", subjects='" + subjects + '\'' +
                ", publishdate=" + publishdate +
                ", tags='" + tags + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return (this.title).compareTo(((Course)o).title);
    }
}

