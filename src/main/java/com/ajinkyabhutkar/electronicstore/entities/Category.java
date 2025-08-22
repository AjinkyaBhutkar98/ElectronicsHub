package com.ajinkyabhutkar.electronicstore.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="categories")
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "category_title",length = 50,unique = true,nullable = false)
    private String title;

    @Column(name = "category_description",length = 200)
    private String description;

    @Column
    private String coverImage;

    @Column
    private boolean deleted=false;

//    @Transient
//    private String etcInfo;


    public Category() {
    }

    public Category(String coverImage, Long id, String title, String description, boolean deleted) {
        this.coverImage = coverImage;
        Id = id;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
