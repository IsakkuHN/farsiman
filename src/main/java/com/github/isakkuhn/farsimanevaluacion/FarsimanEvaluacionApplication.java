package com.github.isakkuhn.farsimanevaluacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FarsimanEvaluacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarsimanEvaluacionApplication.class, args);
    }

}
