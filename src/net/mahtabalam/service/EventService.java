package net.mahtabalam.service;

import java.util.Date;

import net.mahtabalam.dao.EventDAO;
import net.mahtabalam.entity.Event;

public class EventService {
	 private UserService userService;
	 private EventDAO eventDAO;
	
	 public EventService(UserService userService) {
		this.userService = userService;
		this.eventDAO = new EventDAO();
	 }
	
	 public void addEvent(String eventId, String eventName, String prizeName, Date date) throws Exception {
	        if (eventDAO.exists(eventId)) {
	            throw new Exception("Event already exists for the day");
	        }
	        Event event = new Event(eventId, eventName, prizeName, date);
	        eventDAO.addEvent(event);
	        System.out.println(eventName + " with prize " + prizeName + " added successfully");
	 }
	
	public void registerMember(String eventId, String userId) throws Exception {
        if (!userService.exists(userId)) {
            throw new Exception("Member not found");
        }
        if (!eventDAO.exists(eventId)) {
            throw new Exception("Event not found");
        }
        Event event = eventDAO.getEvent(eventId);
        event.registerMember(userId);
        System.out.println(userService.getMember(userId).getName() + " registered to the " + event.getEventName() + " event successfully");
    }
	
	public boolean exists(String eventId) {
        return eventDAO.exists(eventId);
    }
	
	public Event getEvent(String eventId) {
        return eventDAO.getEvent(eventId);
    }

}
