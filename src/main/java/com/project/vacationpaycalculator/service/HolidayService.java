package com.project.vacationpaycalculator.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class HolidayService {
    public static boolean isHoliday(LocalDate date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        if (month == 1 && day <= 7) return true; // Новый год
        if (month == 2 && day == 23) return true; // День защитника Отечества
        if (month == 3 && day == 8) return true; // 8 Марта
        if (month == 5 && day == 1 || day == 9) return true; // Майские
        if (month == 6 && day == 12) return true; // День России
        if (month == 11 && day == 4) return true; // День Народного Единства
        return false;
    }

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
