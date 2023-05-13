package com.project.vacationpaycalculator.service;

import com.project.vacationpaycalculator.dto.VacationPayDTO;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
public class VacationServiceImpl implements VacationService {
    final int NUMBER_OF_MONTHS = 12;
    final int NUMBER_OF_DAYS_PER_YEAR = 365;

    @Override
    public VacationPayDTO calculate(int avgMonthlySalary, int vacationDays, List<LocalDate> dates) {
        float totalVacationPay;
        if (dates != null) {
            for (LocalDate date : dates) {
                if (HolidayService.isWeekend(date) || HolidayService.isHoliday(date)) {
                    vacationDays--;
                }
            }
        }
        totalVacationPay = getTotalVacationPay(avgMonthlySalary, vacationDays);
        return new VacationPayDTO(totalVacationPay);
    }

    private int getTotalVacationPay(int avgMonthlySalary, int vacationDays) {
        return avgMonthlySalary * NUMBER_OF_MONTHS * vacationDays / NUMBER_OF_DAYS_PER_YEAR;
    }
}