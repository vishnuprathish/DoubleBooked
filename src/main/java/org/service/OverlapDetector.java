package org.service;

import org.domain.Conflict;
import org.domain.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OverlapDetector {
    /**
     * NlogN event overlap detection from a list of unordered events
     * @param eventList list of events to detect
     * @return
     */
    public List<Conflict> detectOverlap(List<Event> eventList) {
        List<Conflict> conflicts = new ArrayList<Conflict>();

        Collections.sort(eventList); // by starttime. to solve it in N later

        int eventListSize = eventList.size();
        if(eventListSize <= 1) { // no conflict
            return new ArrayList<Conflict>();
        }

        for (int i = 1; i < eventListSize; i++) {
            Event e = eventList.get(i);
            Event prev = eventList.get(i-1);

            if(e.getStartTime().isBefore(prev.getEndTime())) {
                conflicts.add(new Conflict(e, prev));
            }
        }
        return conflicts;
    }

}
