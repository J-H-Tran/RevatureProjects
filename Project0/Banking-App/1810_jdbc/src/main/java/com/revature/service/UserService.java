package com.revature.service;

import java.util.List;

import com.revature.dao.BalanceImplDao;
import com.revature.dao.UserImplDao;
import com.revature.model.Balance;
import com.revature.model.User;

public class UserService {

	private static UserService usrService;
	private UserService() {
	}
	
	public static UserService getUserService() {
		if(usrService == null) {
			usrService = new UserService();
		}
		return usrService;
	}
	
	public User getUserDetails(String username){
		return UserImplDao.getUserDao().getUser(username);
	}
	public List<String> listAllUsers(){
		return UserImplDao.getUserDao().getAllUsers();
	}
	public boolean registerUserProcs(User user, Balance balance) {
		return UserImplDao.getUserDao().insertUserProcedure(user, balance);
	}
	//boolean checkInsert(String username);
	public boolean approvalStatus(User user) {
		return UserImplDao.getUserDao().checkApproval(user);
	}
	//boolean verifyUser(int accNumber);
	//boolean checkUserAuthorization(User user);
	public boolean depositMoney(User user, int deposAmount) {
		return BalanceImplDao.getBalanceDao().depositAmount(user, deposAmount);
	}
	public boolean withdrawMoney(User user, int deposAmount) {
		return BalanceImplDao.getBalanceDao().withdrawAmount(user, deposAmount);
	}
	

}
