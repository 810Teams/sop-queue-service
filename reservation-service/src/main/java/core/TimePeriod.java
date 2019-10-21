package core;

import exceptions.InvalidTimePeriodException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimePeriod {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimePeriod() {
        this(LocalDateTime.now().plusMinutes(60), LocalDateTime.now().plusMinutes(75));
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
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private void checkException() throws InvalidTimePeriodException {
        // Private Instance Method: Check for exceptions using current start and end date time attribute value
        if (this.startDateTime.compareTo(this.endDateTime) >= 0) {
            throw new InvalidTimePeriodException("Start datetime must be before end datetime.");
        }
    }

    private void checkException(LocalDateTime startDateTime, LocalDateTime endDateTime) throws InvalidTimePeriodException {
        // Private Instance Method: Check for exceptions using time in LocalDateTime format
        if (startDateTime.compareTo(endDateTime) >= 0) {
            throw new InvalidTimePeriodException("Start datetime must be before end datetime.");
        }
    }

    private void checkException(String startDateTimeString, String endDateTimeString) throws InvalidTimePeriodException {
        // Private Instance Method: Check for exceptions using time in String format
        LocalDateTime startDateTime = this.stringToLocalDateTime(startDateTimeString);
        LocalDateTime endDateTime = this.stringToLocalDateTime(endDateTimeString);

        if (startDateTime.compareTo(endDateTime) >= 0) {
            throw new InvalidTimePeriodException("Start datetime must be before end datetime.");
        }
    }
}
