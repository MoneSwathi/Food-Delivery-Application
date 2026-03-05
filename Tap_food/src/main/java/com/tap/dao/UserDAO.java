package com.tap.dao;

import java.util.List;

import com.tap.model.User;//cnt+shift+o

public interface UserDAO {
	boolean addUser(User user);
	User getUser(String email);
	User getUserId(int userId);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUsers();
}