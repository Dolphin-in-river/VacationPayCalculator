package com.project.vacationpaycalculator.service;

import com.project.vacationpaycalculator.dto.VacationPayDTO;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
public class VacationServiceImpl implements VacationService {
    @Override
    public VacationPayDTO calculate(int avgMonthlySalary, int vacationDays, List<LocalDate> dates) {
        int totalVacationPay;
        if (dates == null) {
            totalVacationPay = avgMonthlySalary * 12 * vacationDays / 365;
        } else {
            totalVacationPay = 0;
            for (LocalDate date : dates) {
                if (!HolidayService.isHoliday(date) && !HolidayService.isWeekend(date)) {
                    totalVacationPay += avgMonthlySalary / 30;
                    vacationDays--;
                }
            }
            totalVacationPay += avgMonthlySalary * vacationDays / 30;
        }
        return new VacationPayDTO(totalVacationPay);
    }
}