package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimePeriod {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimePeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public TimePeriod(String startDateTime, String endDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.startDateTime = LocalDateTime.parse(startDateTime, formatter);
        this.endDateTime = LocalDateTime.parse(endDateTime, formatter);
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
}
