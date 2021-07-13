package com.oftekfak.emagazine.model.post;

public class TagModel {
    private Integer id;
    private String shortCode;
    private String name;
    private Integer categoryId;
    private String category;

    public TagModel(Integer id, String shortCode, String name, Integer categoryId, String category) {
        this.id = id;
        this.shortCode = shortCode;
        this.name = name;
        this.categoryId = categoryId;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
