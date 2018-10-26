package com.revature.dao;

import com.revature.model.Balance;
import com.revature.model.User;

public interface BalanceDao {
	
	public boolean insertBalanceProcedure(User user, Balance balance);
	public int getAccountBalance(User user);
	public boolean depositAmount(User user, int deposAmount);
	public boolean withdrawAmount(User user, int deposAmount);

}
