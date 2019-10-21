package core;

import exceptions.InvalidTimePeriodException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimePeriod {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimePeriod() {
    }

    public TimePeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public TimePeriod(String startDateTime, String endDateTime) {
        this.startDateTime = this.stringToLocalDateTime(startDateTime);
        this.endDateTime = this.stringToLocalDateTime(endDateTime);
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public Duration getDuration() {
        return Duration.between(this.startDateTime, this.endDateTime);
    }

    public long getDurationAsMinute() {
        return this.getDuration().toMinutes();
    }

    private LocalDateTime stringToLocalDateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(time, formatter);
    }

    private void checkException() throws InvalidTimePeriodException {
        if (this.startDateTime.compareTo(this.endDateTime) >= 0) {
            throw new InvalidTimePeriodException("Start datetime must be before end datetime.");
        }
    }

    private void checkException(LocalDateTime startDateTime, LocalDateTime endDateTime) throws InvalidTimePeriodException {
        if (startDateTime.compareTo(endDateTime) >= 0) {
            throw new InvalidTimePeriodException("Start datetime must be before end datetime.");
        }
    }

    private void checkException(String startDateTimeString, String endDateTimeString) throws InvalidTimePeriodException {
        LocalDateTime startDateTime = this.stringToLocalDateTime(startDateTimeString);
        LocalDateTime endDateTime = this.stringToLocalDateTime(endDateTimeString);

        if (startDateTime.compareTo(endDateTime) >= 0) {
            throw new InvalidTimePeriodException("Start datetime must be before end datetime.");
        }
    }
}
