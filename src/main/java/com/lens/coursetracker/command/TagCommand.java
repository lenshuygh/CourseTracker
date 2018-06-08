package com.lens.coursetracker.command;

import javax.validation.constraints.NotEmpty;

public class TagCommand {
    private Integer id;
    @NotEmpty
    private String tagName;

    public TagCommand() {
    }

    public TagCommand(Integer id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
