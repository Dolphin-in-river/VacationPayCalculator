package com.project.vacationpaycalculator.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class HolidayService {
    public static boolean isHoliday(LocalDate date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        // Проверка на праздники
        if (month == 1 && day == 1) return true; // Новый год
        if (month == 2 && day == 23) return true; // День защитника Отечества

        return false;
    }

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
