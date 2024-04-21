package com.github.isakkuhn.farsimanevaluacion.presentation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/initial")
public class InitialController {

    @GetMapping()
    public String getInitial() {
        return "Hello World";
    }
}
