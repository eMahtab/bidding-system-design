package net.mahtabalam.dao;

import java.util.HashMap;
import java.util.Map;

import net.mahtabalam.entity.Event;

public class EventDAO {
    private Map<String, Event> events = new HashMap<>();

    public void addEvent(Event event) {
        events.put(event.getEventId(), event);
    }

    public Event getEvent(String eventId) {
        return events.get(eventId);
    }

    public boolean exists(String eventId) {
        return events.containsKey(eventId);
    }
}