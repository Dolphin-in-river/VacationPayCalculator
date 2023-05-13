package com.project.vacationpaycalculator.service;

import com.project.vacationpaycalculator.dto.VacationPayDTO;

import java.time.LocalDate;
import java.util.List;

public interface VacationService {
    VacationPayDTO calculate(int avgMonthlySalary, int vacationDays, List<LocalDate> dates);
}
