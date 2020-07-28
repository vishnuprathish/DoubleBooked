package org.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event implements Comparable<Event> {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String title; // identifier will be an auto-gen Id when there is a db behind this


    public Event(LocalDateTime startTime, LocalDateTime endTime, String title) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return getStartTime().equals(event.getStartTime()) &&
                getEndTime().equals(event.getEndTime()) &&
                getTitle().equals(event.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartTime(), getEndTime(), getTitle());
    }

    @Override
    public int compareTo(Event o) {
        return this.getStartTime().compareTo(o.getStartTime());
    }
}
