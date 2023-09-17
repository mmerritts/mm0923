package app.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class HolidayService {
    Set<LocalDate> mockHolidayDatabase;

    HolidayService() {
        this.mockHolidayDatabase = populateMockHolidayDatabase();
    }

    public boolean isDateAHoliday(LocalDate date) {
        return mockHolidayDatabase.contains(date);
    }

    public boolean isDateAWeekEnd(LocalDate date) {
        var weekendSet = Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        return weekendSet.contains(date.getDayOfWeek());
    }

    private Set<LocalDate> populateMockHolidayDatabase() {
        Set<LocalDate> holidayDateList = new HashSet<>();
        for (int year = 1950; year <= 2250; year++) {
            addFourthOfJulyHolidayForYear(year, holidayDateList);
            addLaborDayHolidayForYear(year, holidayDateList);
        }

        return holidayDateList;
    }

    private void addLaborDayHolidayForYear(int year, Set<LocalDate> holidayDateList) {
        for (int i = 1; i <= 7; i++) {
            var checkDay = LocalDate.of(year, 9, i);
            if (checkDay.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
                holidayDateList.add(checkDay);
            }
        }
    }

    private void addFourthOfJulyHolidayForYear(int year, Set<LocalDate> holidayDateList) {
        var fourthOfJuly = LocalDate.of(year, 7, 4);
        if (fourthOfJuly.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            holidayDateList.add(LocalDate.of(year, 7, 3));
        } else if (fourthOfJuly.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            holidayDateList.add(LocalDate.of(year, 7, 5));
        } else {
            holidayDateList.add(fourthOfJuly);
        }
    }
}
