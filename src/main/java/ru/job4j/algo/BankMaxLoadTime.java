package ru.job4j.algo;

import java.util.ArrayList;
import java.util.List;

public class BankMaxLoadTime {

    public static int[] findMaxLoadTime(List<int[]> visitTimes) {
        int maxLoadStartTime = 0;
        int maxLoadEndTime = 0;
        int maxLoad = 0;
        int currentLoad = 0;
        List<Event> events = new ArrayList<>(visitTimes.size() * 2);

        for (int[] time : visitTimes) {
            events.add(new Event(time[0], EventType.ARRIVAL));
            events.add(new Event(time[1], EventType.DEPARTURE));
        }

        events.sort(Event::compareTo);

        for (Event event : events) {
            if (event.type == EventType.ARRIVAL) {
                currentLoad++;
                if (maxLoad < currentLoad) {
                    maxLoad++;
                    maxLoadStartTime = event.time;
                }
            } else {
                if (currentLoad == maxLoad && maxLoadEndTime == 0 && event.time != maxLoadStartTime) {
                    maxLoadEndTime = event.time;
                }
                currentLoad--;
            }
        }

        return new int[]{maxLoadStartTime, maxLoadEndTime};
    }

    static class Event implements Comparable<Event> {
        int time;
        EventType type;

        Event(int time, EventType type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event other) {
            if (this.time == other.time) {
                return this.type == EventType.ARRIVAL ? -1 : 1;
            }
            return Integer.compare(this.time, other.time);
        }
    }

    enum EventType {
        ARRIVAL, DEPARTURE
    }
}

