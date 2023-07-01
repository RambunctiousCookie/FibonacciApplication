package com.example.oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class OracleSubmission {

    public static void main(String[] args) {
        SpringApplication.run(OracleSubmission.class, args);
    }

}
