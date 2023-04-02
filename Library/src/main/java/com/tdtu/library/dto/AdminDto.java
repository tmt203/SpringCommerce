package com.tdtu.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AdminDto {
    @Size(min = 3, max = 10, message = "Invalid first name! (3-10 characters)")
    private String firstName;

    @Size(min = 3, max = 10, message = "Invalid last name! (3-10 characters)")
    private String lastName;

    private String username;

    @Size(min = 6, max = 15, message = "Invalid password! (6-15 characters)")
    private String password;
    private String confirmPassword;

}
