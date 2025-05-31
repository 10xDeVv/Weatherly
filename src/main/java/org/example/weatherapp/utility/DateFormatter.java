package org.example.weatherapp.utility;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DateFormatter {
    public static String formatTo12HourTime(String dateTimeText) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("h:mm a");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, inputFormat);
        return dateTime.format(outputFormat);
    }

    public static String formatToDayAndTime(String dateTimeText) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("EEE, h:mm a");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeText, inputFormat);
        return dateTime.format(outputFormat);
    }

    public static List<String> getWeekFrom(String dateText) {
        LocalDate date = LocalDate.parse(dateText);
        DayOfWeek startDay = date.getDayOfWeek();

        List<String> week = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            DayOfWeek day = startDay.plus(i);
            String shortName = day.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            week.add(shortName);
        }
        return week;
    }
}
