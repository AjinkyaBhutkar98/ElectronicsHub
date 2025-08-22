package com.ajinkyabhutkar.electronicstore.dtos;

import com.ajinkyabhutkar.electronicstore.Validate.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDto {

    @NotBlank(message = "title should not be blank!!")
    @Size(min = 3,max = 50)
    private String title;

    @NotBlank(message = "description should not be blank!!")
    @Size(min = 5,max = 50)
    private String description;

    @ImageNameValid(message = "please select image!")
    private String coverImage;

    public CategoryDto() {
    }

    public CategoryDto(String title, String description, String coverImage) {
        this.title = title;
        this.description = description;
        this.coverImage = coverImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
