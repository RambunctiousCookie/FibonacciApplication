package com.example.oracle.dto;

import lombok.Data;

@Data
public class FibonacciResponseStringDTO {

    private final String[] fibonacci;
    private final String[] sorted;

    public FibonacciResponseStringDTO(String[] fibonacci, String[] sorted){
        this.fibonacci = fibonacci;
        this.sorted = sorted;
    }

}