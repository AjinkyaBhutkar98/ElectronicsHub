package com.ajinkyabhutkar.electronicstore.dtos;

import com.ajinkyabhutkar.electronicstore.Validate.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @NotBlank(message = "title should not be blank!!")
    @Size(min = 3,max = 50)
    private String title;

    @NotBlank(message = "description should not be blank!!")
    @Size(min = 5,max = 50)
    private String description;

    @ImageNameValid(message = "please select image!")
    private String coverImage;

}
