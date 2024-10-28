package net.mahtabalam.dao;

import java.util.HashMap;
import java.util.Map;

import net.mahtabalam.entity.User;

public class UserDAO {
    private Map<String, User> users = new HashMap<>();

    public void addMember(User user) {
        users.put(user.getUserId(), user);
    }

    public User getMember(String userId) {
        return users.get(userId);
    }

    public boolean exists(String userId) {
        return users.containsKey(userId);
    }
}