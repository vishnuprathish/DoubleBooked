package org.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event implements Comparable<Event> {
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public Event(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return getStartTime().equals(event.getStartTime()) &&
                getEndTime().equals(event.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartTime(), getEndTime());
    }

    @Override
    public int compareTo(Event o) {
        return this.getStartTime().compareTo(o.getStartTime());
    }
}
