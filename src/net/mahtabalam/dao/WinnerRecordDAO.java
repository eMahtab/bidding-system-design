package net.mahtabalam.dao;

import java.util.ArrayList;
import java.util.List;

import net.mahtabalam.entity.WinnerRecord;

public class WinnerRecordDAO {
    private List<WinnerRecord> winnerRecords = new ArrayList<>();

    public void addWinner(WinnerRecord record) {
        winnerRecords.add(record);
    }

    public List<WinnerRecord> getAllWinners() {
        return winnerRecords;
    }
}