package com.api.wallet.db.entity;

import java.io.Serializable;
import java.util.Objects;

public class Categories implements Serializable {
    private String categoriesId;
    private String name;
    private String type;


    public Categories(String categoriesId, String type, String name) {
        this.categoriesId = categoriesId;
        this.type = type;
        this.name = name;
    }

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Categories that = (Categories) object;
        return Objects.equals(categoriesId, that.categoriesId) && Objects.equals(type, that.type) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoriesId, type, name);
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categoriesId='" + categoriesId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
