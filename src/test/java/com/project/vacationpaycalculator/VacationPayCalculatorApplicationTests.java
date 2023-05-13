package com.project.vacationpaycalculator;

import com.project.vacationpaycalculator.dto.VacationPayDTO;
import com.project.vacationpaycalculator.service.VacationService;
import com.project.vacationpaycalculator.service.VacationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VacationPayCalculatorApplicationTests {


    private final VacationService service = new VacationServiceImpl();

    @Test
    public void calculateWithoutDates() {
        int avgMonthlySalary = 100000;
        int vacationDays = 14;

        VacationPayDTO dto = service.calculate(avgMonthlySalary, vacationDays, null);

        assertEquals(46027, 3, dto.getTotalVacationPay());
    }

    @Test
    public void calculateWithDatesWithoutHolidaysAndWeekends() {
        int avgMonthlySalary = 36500;
        int vacationDays = 3;
        List<LocalDate> dates = Arrays.asList(
                LocalDate.of(2023, 1, 10),
                LocalDate.of(2023, 1, 11),
                LocalDate.of(2023, 1, 12)
        );
        VacationPayDTO dto = service.calculate(avgMonthlySalary, vacationDays, dates);
        assertEquals(3600, dto.getTotalVacationPay());
    }

    @Test
    public void calculateWithDatesWithHolidaysAndWeekends() {
        int avgMonthlySalary = 36500;
        int vacationDays = 6;
        List<LocalDate> dates = Arrays.asList(
                LocalDate.of(2023, 1, 8),
                LocalDate.of(2023, 1, 11),
                LocalDate.of(2023, 1, 12),
                LocalDate.of(2023, 1, 13),
                LocalDate.of(2023, 1, 14),
                LocalDate.of(2023, 1, 15)
        );
        VacationPayDTO dto = service.calculate(avgMonthlySalary, vacationDays, dates);
        assertEquals(3600, dto.getTotalVacationPay());
    }

    @Test
    public void calculateWithDatesWithOnlyHolidaysAndWeekends() {
        int avgMonthlySalary = 100000;
        int vacationDays = 6;
        List<LocalDate> dates = Arrays.asList(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 2),
                LocalDate.of(2023, 1, 3),
                LocalDate.of(2023, 3, 8),
                LocalDate.of(2023, 6, 12),
                LocalDate.of(2023, 11, 4)
        );
        VacationPayDTO dto = service.calculate(avgMonthlySalary, vacationDays, dates);
        assertEquals(0, dto.getTotalVacationPay());
    }

    @Test()
    public void calculateWithDatesWithDateRepeated() {
        try {
            int avgMonthlySalary = 100000;
            int vacationDays = 2;
            List<LocalDate> dates = Arrays.asList(
                    LocalDate.of(2023, 1, 1),
                    LocalDate.of(2023, 1, 1)
            );
            VacationPayDTO dto = service.calculate(avgMonthlySalary, vacationDays, dates);
        } catch (IllegalArgumentException e) {
            assertEquals("The dates are repeated", e.getMessage());
        }
    }

    @Test()
    public void calculateWithDatesWithIncorrectDayCount() {
        try {
            int avgMonthlySalary = 100000;
            int vacationDays = 10;
            List<LocalDate> dates = List.of(
                    LocalDate.of(2023, 1, 1)
            );
            VacationPayDTO dto = service.calculate(avgMonthlySalary, vacationDays, dates);
        } catch (IllegalArgumentException e) {
            assertEquals("Vacation Days must match with the size of dates", e.getMessage());
        }
    }
}
