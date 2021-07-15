package com.oftekfak.emagazine.model.post;

import com.oftekfak.emagazine.entity.TagEntity;

public class TagModel {
    private Long id;
    private String shortCode;
    private String name;
    private Integer categoryId;
    private String category;

    public TagModel(Long id, String shortCode, String name, Integer categoryId, String category) {
        this.id = id;
        this.shortCode = shortCode;
        this.name = name;
        this.categoryId = categoryId;
        this.category = category;
    }

    public TagModel(TagEntity t) {
        this.id = t.getId();
        this.shortCode = t.getShortCode();
        this.name = t.getName();
        this.categoryId = t.getCategoryId();
        this.category = t.getCategory();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
