package com.project.vacationpaycalculator.controller;

import com.project.vacationpaycalculator.dto.VacationPayDTO;
import com.project.vacationpaycalculator.service.VacationService;
import com.project.vacationpaycalculator.service.VacationServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Configuration
@ComponentScan("com.project.vacationpaycalculator")
@RestController
public class VacationController {

    @GetMapping("/calculate")
    public VacationPayDTO calculate(@RequestParam int avgMonthlySalary,
                                    @RequestParam int vacationDays,
                                    @RequestParam(required = false) List<LocalDate> dates) {
        try {
            VacationService service = new VacationServiceImpl();
            return service.calculate(avgMonthlySalary, vacationDays, dates);
        }
        catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request parameter", ex);
        }
    }
}