package net.mahtabalam.service;

import net.mahtabalam.dao.UserDAO;
import net.mahtabalam.entity.User;

public class UserService {
	private UserDAO userDAO;
	
	public UserService() {
		this.userDAO = new UserDAO();
	}
	
	public void addMember(String userId, String name, int superCoins) throws Exception {
        if (superCoins < 1) {
            throw new Exception("Super coins must be at least one");
        }
        User user = new User(userId, name, superCoins);
        userDAO.addMember(user);
        System.out.println(name + " added successfully");
    }
	
	public boolean exists(String userId) {
        return userDAO.exists(userId);
    }
	
	public User getMember(String userId) {
        return userDAO.getMember(userId);
    }
	
}
