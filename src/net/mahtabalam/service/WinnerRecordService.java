package net.mahtabalam.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.mahtabalam.dao.WinnerRecordDAO;
import net.mahtabalam.entity.WinnerRecord;

public class WinnerRecordService {
	private WinnerRecordDAO winnerDAO;
	
	public WinnerRecordService() {
		this.winnerDAO = new WinnerRecordDAO();
	}
	
	public void addWinner(WinnerRecord record) {
		winnerDAO.addWinner(record);
    }

    public List<WinnerRecord> getAllWinners() {
        return winnerDAO.getAllWinners();
    }
    
    public void listWinners(String orderBy) {
        List<WinnerRecord> sortedWinners = new ArrayList<>(winnerDAO.getAllWinners());
        if (orderBy.equalsIgnoreCase("asc")) {
            sortedWinners.sort(Comparator.comparing(w -> w.getEventDate()));
        } else if (orderBy.equalsIgnoreCase("desc")) {
            sortedWinners.sort((w1, w2) -> w2.getEventDate().compareTo(w1.getEventDate()));
        }
        System.out.println(sortedWinners);
    }
	
}
