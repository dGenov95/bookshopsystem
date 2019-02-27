package app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import app.models.base.BaseEntity;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    private String name;

    public Category() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
