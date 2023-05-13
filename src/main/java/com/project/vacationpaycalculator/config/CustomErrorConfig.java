package com.project.vacationpaycalculator.config;

import com.project.vacationpaycalculator.controller.CustomErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;



public class CustomErrorConfig {
    @Bean
    public CustomErrorController customErrorController(ErrorAttributes errorAttributes) {
        return new CustomErrorController(errorAttributes);
    }
}