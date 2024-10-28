package net.mahtabalam.entity;

import java.util.UUID;

public class User {
	private String userId;
	private String name;
	private int superCoins;
	
	public User(String name) {
		this.name = name;
		this.userId = UUID.randomUUID().toString();
	}
	public User(String name, int superCoins) {
		this.name = name;
		this.userId = UUID.randomUUID().toString();
		this.superCoins = superCoins;
	}
	public User(String id, String name, int superCoins) {
		this.userId = id;
		this.name = name;
		this.superCoins = superCoins;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSuperCoins() {
		return superCoins;
	}
	public void setSuperCoins(int superCoins) {
		this.superCoins = superCoins;
	}
	
	@Override
    public String toString() {
        return name;
    }
	
	public boolean hasEnoughCoins(int maxBid) {
        return this.superCoins >= maxBid;
    }
	
	public void deductCoins(int maxBid) {
        this.superCoins -= maxBid;
    }
	
}
