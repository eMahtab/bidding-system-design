package net.mahtabalam.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Event {
    private String eventId;
	private String eventName;
    private String prizeName;
    private Date eventDate;
    private Map<String, List<Integer>> userBids;  // UserId -> Bids
    private List<String> registeredUsers;
    
    public String getPrizeName() {
		return prizeName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public Map<String, List<Integer>> getUserBids() {
		return userBids;
	}

	public List<String> getRegisteredUsers() {
		return registeredUsers;
	}
	
	public Event(String eventId, String eventName, String prizeName, Date eventDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.prizeName = prizeName;
        this.eventDate = eventDate;
        this.userBids = new HashMap<>();
        this.registeredUsers = new ArrayList<>();
    }

    public Event(String eventName, String prizeName, Date eventDate) {
        this.eventId = UUID.randomUUID().toString();
        this.eventName = eventName;
        this.prizeName = prizeName;
        this.eventDate = eventDate;
        this.userBids = new HashMap<>();
        this.registeredUsers = new ArrayList<>();
    }
    
    public String getEventId() {
		return eventId;
	}

    public void registerMember(String userId) throws Exception {
        if (registeredUsers.contains(userId)) {
            throw new Exception("User already registered for this event");
        }
        registeredUsers.add(userId);
    }

    public boolean isMemberRegistered(String userId) {
        return registeredUsers.contains(userId);
    }

    public void submitBids(String userId, List<Integer> bids) throws Exception {
        if (bids.size() > 5 || bids.isEmpty()) {
            throw new Exception("You can submit up to 5 bids only");
        }
        if (userBids.containsKey(userId)) {
            throw new Exception("User has already submitted bids for this event");
        }
        Set<Integer> bidSet = new HashSet<>(bids);
        if (bidSet.size() != bids.size()) {
            throw new Exception("All bids must be unique");
        }
        userBids.put(userId, bids);
    }

    public List<Integer> getMemberBids(String userId) {
        return userBids.get(userId);
    }

    public List<Integer> getAllBids() {
        List<Integer> allBids = new ArrayList<>();
        for (List<Integer> bids : userBids.values()) {
            allBids.addAll(bids);
        }
        return allBids;
    }

	public String getEventName() {
		return eventName;
	}
}