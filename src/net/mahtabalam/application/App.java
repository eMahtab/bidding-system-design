package net.mahtabalam.application;

import java.util.Arrays;
import java.util.Date;

import net.mahtabalam.service.BiddingService;
import net.mahtabalam.service.EventService;
import net.mahtabalam.service.UserService;
import net.mahtabalam.service.WinnerRecordService;

public class App {
	public static void main(String[] args)  {
		// Instantiate DAOs
        UserService userService = new UserService();
        EventService eventService = new EventService(userService);
        WinnerRecordService winnerRecordService = new WinnerRecordService();

        // Instantiate service
        BiddingService biddingService = new BiddingService(userService, eventService, winnerRecordService);
        try{
        	 // Example operations
            userService.addMember("u1", "Akshay", 10000);
            userService.addMember("u2", "Chris", 5000);

            // Add events
            eventService.addEvent("e1", "BBD", "IPHONE-14", new Date());
            
            // Register members 
            eventService.registerMember("e1", "u1");
            eventService.registerMember("e1", "u2");

            // Submit bids
            biddingService.submitBid("e1", "u1", Arrays.asList(100, 200, 400, 500, 600));
            biddingService.submitBid("e1", "u2", Arrays.asList(150, 250, 350, 450, 550));

            // Declare winner
            biddingService.declareWinner("e1");

            // List past winners
            winnerRecordService.listWinners("asc");
            
        } catch(Exception e) {
        	e.printStackTrace();
        }
       
	}
}
