package net.mahtabalam.entity;

import java.util.Date;

public class WinnerRecord {
    private String eventId;
    private String winnerName;
    private int lowestBid;
    private Date eventDate;

    public WinnerRecord(String eventId, String winnerName, int lowestBid, Date eventDate) {
        this.eventId = eventId;
        this.winnerName = winnerName;
        this.lowestBid = lowestBid;
        this.eventDate = eventDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        return "{eventId=" + eventId + ", winnerName=" + winnerName + ", lowestBid=" + lowestBid + ", eventDate=" + eventDate + "}";
    }
}