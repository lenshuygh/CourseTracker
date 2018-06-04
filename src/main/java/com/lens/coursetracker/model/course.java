package com.lens.coursetracker.model;

import java.util.Date;

public class course {
    /*
    id int not null,
	title varchar(50) not null,
	author varchar (30),
	url varchar (150) not null,
	subjects varchar(200),
	publishdate date,
	tags varchar (100),
     */

    private Integer id;
    private String title;
    private String author;
    private String url;
    private String subjects;
    private Date publishdate;
    private String tags;

    public course(Integer id, String title, String author, String url, String subjects, Date publishdate, String tags) {
        this.id = id;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", subjects='" + subjects + '\'' +
                ", publishdate=" + publishdate +
                ", tags='" + tags + '\'' +
                '}';
    }
}

