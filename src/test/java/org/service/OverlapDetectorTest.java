package org.service;

import org.domain.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OverlapDetectorTest {



    @Test
    void detectOverlapSimpleNoOverLap() {

        // no overlap with unordered events
        List<Event> testList  = new ArrayList<>(
                List.of(
                        new Event(
                                LocalDateTime.parse("2019-03-29T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T10:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 1"
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T09:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 2"
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T10:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T11:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 3"
                        ),
                        new Event(
                                LocalDateTime.parse("2020-03-29T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2020-03-29T10:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 4"
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T11:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T12:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 5"
                        )

                )
        );

        OverlapDetector fixture = new OverlapDetector();
        assertTrue(fixture.detectOverlap(testList).isEmpty());
    }

    private List<Event> testList1() {
       return new ArrayList<>(
                List.of(
                        new Event(
                                LocalDateTime.parse("2019-03-29T10:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T10:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 1"
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T10:15:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T11:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 2"
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T04:15:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T07:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 3"
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T06:20:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T07:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 4"
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T07:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T08:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 5"
                        ),
                        new Event(
                                LocalDateTime.parse("2019-03-29T07:30:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                LocalDateTime.parse("2019-03-29T08:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                "Event 6"
                        )

                )
        );
    }

    @Test
    void detectOverlapSimpleOverLap() {

        // Happy path two overlaps
        List<Event> testList = testList1();

        OverlapDetector fixture = new OverlapDetector();
        assertEquals(3, fixture.detectOverlap(testList).size());
    }

}