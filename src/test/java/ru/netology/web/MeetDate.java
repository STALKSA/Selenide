package ru.netology.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MeetDate {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
