package org.service;

import org.domain.Conflict;
import org.domain.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OverlapDetectorTest {

    Event c1 = new Event(
            LocalDateTime.parse("2019-03-29T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            LocalDateTime.parse("2019-03-29T10:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    );

    Event c2 = new Event(
            LocalDateTime.parse("2019-03-29T10:15:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            LocalDateTime.parse("2019-03-29T11:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    );


    // pair 2
    Event c3 = new Event(
            LocalDateTime.parse("2019-03-29T04:15:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            LocalDateTime.parse("2019-03-29T07:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    );

    Event c4 = new Event(
            LocalDateTime.parse("2019-03-29T04:15:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            LocalDateTime.parse("2019-03-29T07:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    );

    // pair 3
    Event c5 = new Event(
            LocalDateTime.parse("2019-03-29T07:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            LocalDateTime.parse("2019-03-29T08:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    );

    Event c6 = new Event(
            LocalDateTime.parse("2019-03-29T07:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            LocalDateTime.parse("2019-03-29T08:03:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    );

    List<Event> testList = new ArrayList<>(
            List.of(c1, c2, c3, c4, c5, c5)
    );


    List<Event> testList2 = new ArrayList<>(
            List.of(c1, c3, c5, c6)
    );


    @Test
    void detectOverlapSimpleNoOverLap() {

        // no overlap with unordered events
        List<Event> testList  = new ArrayList<>(
                List.of(
                        new Event(
                                LocalDateTime.parse("2019-03-29T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T10:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T09:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T10:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T11:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        ),
                        new Event(
                                LocalDateTime.parse("2020-03-29T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2020-03-29T10:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T11:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T12:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                        )

                )
        );

        OverlapDetector fixture = new OverlapDetector();
        assertTrue(fixture.detectOverlap(testList).isEmpty());
    }


    @Test
    void detectOverlapSimpleOverLap() {
        // Happy path three overlaps
        // pair 1

        OverlapDetector fixture = new OverlapDetector();
        assertEquals(3, fixture.detectOverlap(testList).size());
        Collections.shuffle(testList); // check for sorting
        assertEquals(3, fixture.detectOverlap(testList).size());
    }


    @Test
    void detectOverlapEquality() {
        // Happy path three overlaps
        // pair 1

        OverlapDetector fixture = new OverlapDetector();
        List<Conflict> conflicts = fixture.detectOverlap(testList2);

        assertEquals(conflicts.get(0).getFirst(), c6);

    }

}