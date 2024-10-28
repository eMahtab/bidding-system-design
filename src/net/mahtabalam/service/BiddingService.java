package net.mahtabalam.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mahtabalam.entity.Event;
import net.mahtabalam.entity.User;
import net.mahtabalam.entity.WinnerRecord;

public class BiddingService {
    private UserService userService;
    private EventService eventService;
    private WinnerRecordService winnerRecordService;

    public BiddingService(UserService userService, EventService eventService, WinnerRecordService winnerRecordService) {
        this.userService = userService;
        this.eventService = eventService;
        this.winnerRecordService = winnerRecordService;
    }

    public void submitBid(String eventId, String userId, List<Integer> bids) throws Exception {
        if (!userService.exists(userId)) {
            throw new Exception("Member not found");
        }
        if (!eventService.exists(eventId)) {
            throw new Exception("Event not found");
        }

        Event event = eventService.getEvent(eventId);
        User user = userService.getMember(userId);

        // Ensure member is registered
        if (!event.isMemberRegistered(userId)) {
            throw new Exception("Member did not register for this event");
        }

        // Ensure sufficient coins
        int maxBid = Collections.max(bids);
        if (!user.hasEnoughCoins(maxBid)) {
            throw new Exception("Not enough super coins to submit the bid");
        }

        // Deduct the max bid
        user.deductCoins(maxBid);

        // Submit the bids
        event.submitBids(userId, bids);
        System.out.println("BIDS submitted successfully");
    }

    public void declareWinner(String eventId) throws Exception {
        // Step 1: Check if the event exists
        if (!eventService.exists(eventId)) {
            throw new Exception("Event not found");
        }

        // Retrieve the event
        Event event = eventService.getEvent(eventId);

        // Step 2: Prepare maps to track bid frequencies and submission order of bids
        Map<Integer, Integer> bidFrequency = new HashMap<>();
        Map<Integer, String> bidToMember = new HashMap<>();
        Map<Integer, Long> bidTimeStamp = new HashMap<>(); // To track the time when the bid was first submitted

        long currentTime = 0;  // Simulating a timestamp to track order of submission
        // Iterate over the bids submitted by all members
        for (Map.Entry<String, List<Integer>> entry : event.getUserBids().entrySet()) {
            String memberId = entry.getKey();
            for (int bid : entry.getValue()) {
                // Increment current time to simulate order of submission
                currentTime++;
                // Count frequency of each bid
                bidFrequency.put(bid, bidFrequency.getOrDefault(bid, 0) + 1);
                // Map the bid to the member (only for the first occurrence of that bid)
                bidToMember.putIfAbsent(bid, memberId);
                // Track the first time the bid was submitted
                bidTimeStamp.putIfAbsent(bid, currentTime);
            }
        }

        // Step 3: Find the lowest bid, and if not unique, the earliest submission
        int lowestBid = Integer.MAX_VALUE;
        String winnerMemberId = "";
        long earliestTime = Long.MAX_VALUE; // To track the earliest submission time

        // Iterate over the bid frequencies
        for (Map.Entry<Integer, Integer> entry : bidFrequency.entrySet()) {
            int bid = entry.getKey();
            int frequency = entry.getValue();
            long timestamp = bidTimeStamp.get(bid);  // Retrieve the time when the bid was first submitted

            // We're only interested in the lowest bid (whether unique or not)
            if (bid < lowestBid || (bid == lowestBid && timestamp < earliestTime)) {
                lowestBid = bid;
                winnerMemberId = bidToMember.get(bid);  // Retrieve the member who placed this bid
                earliestTime = timestamp;  // Update to the earliest submission time for this bid
            }
        }

        // Step 4: Declare the winner
        if (winnerMemberId == "") {
            throw new Exception("No valid bids found");
        }

        // Fetch the winner's details
        String winnerName = userService.getMember(winnerMemberId).getName();
        System.out.println(winnerName + " wins the " + event.getPrizeName() + " with lowest bid " + lowestBid);

        // Step 5: Add this winner to the list of past winners
        WinnerRecord winnerRecord = new WinnerRecord(eventId, winnerName, lowestBid, event.getEventDate());
        winnerRecordService.addWinner(winnerRecord);
    }

}
