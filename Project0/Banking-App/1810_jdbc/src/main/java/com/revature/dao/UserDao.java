package com.revature.dao;

import java.util.List;

import com.revature.model.Balance;
import com.revature.model.User;

public interface UserDao {
	
	public User getUser(String username);
	public List<String> getAllUsers();
	boolean insertUserProcedure(User user, Balance balance);
	boolean checkUser(String username);
	boolean checkApproval(User user);
	boolean verifyUser(int accNumber, User user);
	boolean checkUserAuthorization(User user);

}
