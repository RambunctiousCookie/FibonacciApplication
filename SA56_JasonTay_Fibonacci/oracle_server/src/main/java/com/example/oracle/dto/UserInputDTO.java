package com.example.oracle.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserInputDTO {

    @NotNull
    @Min(value = 1, message = "Value must be at least 1")
    @Max(value = 100, message = "Value must be at most 100")
    private int elements;
    public UserInputDTO() {}
    public UserInputDTO(int elements) {
        this.elements = elements;
    }
}
