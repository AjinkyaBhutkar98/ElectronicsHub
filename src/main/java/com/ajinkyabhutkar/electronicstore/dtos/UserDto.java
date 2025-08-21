package com.ajinkyabhutkar.electronicstore.dtos;

import com.ajinkyabhutkar.electronicstore.Validate.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


public class UserDto {

    private Long id;

    @Size(min = 3,max = 40,message = "Invalid username!!")
    private String name;

    @Email(message = "invalid email!!")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @Size(min = 4,max = 6)
    private String gender;

    @NotBlank(message = "write something about yourself!")
    private String about;

    @ImageNameValid
    private String image;

    //@Patern
    //Custom validator

    public UserDto() {
    }

    public UserDto(Long id, String name, String email, String password, String gender, String about, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.about = about;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
