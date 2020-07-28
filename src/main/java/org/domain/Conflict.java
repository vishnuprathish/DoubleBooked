package org.domain;

public class Conflict {
    private Event first;
    private Event second;

    public Conflict(Event first, Event second) {
        this.first = first;
        this.second = second;
    }

    public Event getFirst() {
        return first;
    }

    public void setFirst(Event first) {
        this.first = first;
    }

    public Event getSecond() {
        return second;
    }

    public void setSecond(Event second) {
        this.second = second;
    }
}
