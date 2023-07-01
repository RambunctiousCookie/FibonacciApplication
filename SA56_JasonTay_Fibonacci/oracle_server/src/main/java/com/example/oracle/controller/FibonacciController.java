package com.example.oracle.controller;

import com.example.oracle.dto.UserInputDTO;
import com.example.oracle.dto.FibonacciResponseDTO;
import com.example.oracle.service.FibonacciService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class FibonacciController {

    private final FibonacciService fibonacciService;

    @Autowired
    public FibonacciController(FibonacciService fibonacciService){
        this.fibonacciService=fibonacciService;     //perform constructor injection for best practice; enables immutability and unit testing if needed
    }

    //QueryString for testing digit outputs
    //Example url: http://localhost:8000/api/fibonacci/10
    @CrossOrigin
    @GetMapping("/fibonacci/{elements}")
    public Mono<FibonacciResponseDTO> publishFibonacciResult(@PathVariable("elements") int elements) {
        return fibonacciService.getFibonacciResult(elements);
    }

    //GET Mapping (use POSTMAN or SWAGGER to submit a RequestBody)
    //Example url: http://localhost:8000/api/fibonacci
    @CrossOrigin
    @GetMapping("/fibonacci")
    public Mono<FibonacciResponseDTO> publishFibonacciResultJson (@Valid @RequestBody UserInputDTO request) {
        return fibonacciService.getFibonacciResult(request.getElements());
    }

    //POST Mapping for Axios (React.js implementation)
    //Example url: http://localhost:8000/api/fibonacci/post
    @CrossOrigin
    @PostMapping("/fibonacci/post")
    public Mono<FibonacciResponseDTO> publishFibonacciResultPost (@Valid @RequestBody UserInputDTO request) {
        return fibonacciService.getFibonacciResult(request.getElements());
    }

}
