package com.example.oracle.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class FibonacciResponseDTO {

    private final BigInteger[] fibonacci;
    private final BigInteger[] sorted;

    public FibonacciResponseDTO(BigInteger[] fibonacci, BigInteger[] sorted){
        this.fibonacci = fibonacci;
        this.sorted = sorted;
    }

}
