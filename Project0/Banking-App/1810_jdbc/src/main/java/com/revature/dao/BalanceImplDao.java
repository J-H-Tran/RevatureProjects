package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.Balance;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class BalanceImplDao implements BalanceDao{
	private static BalanceImplDao balDao;
	final static Logger log = Logger.getLogger(UserImplDao.class);
	
	private BalanceImplDao() {
	}
	public static BalanceImplDao getBalanceDao(){
		if (balDao == null){
			balDao = new BalanceImplDao();
		}
		return balDao;
	}
	@Override
	public boolean insertBalanceProcedure(User user, Balance balance) {
		log.info("Updating balance calling stored procedure");
		try (Connection conn = ConnectionUtil.getConnection()) {
			String storeProcs = "call add_balance(?, ?, ?)";
			CallableStatement cs = conn.prepareCall(storeProcs);
			
			cs.setInt(1, user.getId());
			cs.setInt(2, balance.getAccountId());
			cs.setInt(3, balance.getBalanceAmount());
			
			if(cs.executeUpdate()>0) {
				return true;
			} 
		} catch (SQLException s) {
			log.error("Exception in inserBalanceProcedure thrown");
			s.getMessage();
			s.printStackTrace();
		} finally {
			log.warn("InsertBalanceProcedure - executed finally block");
		}log.info("Balance info could not be inserted");
		return false;
	}
	@Override
	public int getAccountBalance(User user) {
		int sum = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			log.info("User attempted to retrieve account balance info.");
			String sql = "select balance_amt from balance_table where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getId());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				sum = rs.getInt("balance_amt");
			}
			return sum;
		} catch (SQLException s) {
			log.error("Exception in getAccountBalance trown");
			s.getMessage();
			s.printStackTrace();
		} finally {
			log.warn("insertAccountBalance - executed finally block");
		}log.warn("Could not retrieve account balance");
		return 0;
	}
	@Override
	public boolean depositAmount(User user, int dAmount) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			log.info("User attempted to deposit");
			String sql = "update balance_table set balance_amt = ? where user_id = ?";
			int sum = 0;
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int temp = getAccountBalance(user);
			
			sum = temp + dAmount;
			pstmt.setInt(1, sum);
			pstmt.setInt(2, user.getId());
			
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException s) {
			log.error("Exception in depositAmount thrown");
			s.getMessage();
			s.printStackTrace();
		} finally {
			log.warn("depositAmount - executed finally block");
		}log.warn("User could not deposit");
		return false;
	}
	@Override
	public boolean withdrawAmount(User user, int wAmount) {
		int sum = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			log.info("User attempted to withdraw");
			String sql = "update balance_table set balance_amt = ? where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			int temp = getAccountBalance(user);
			
			if(temp < wAmount) {
				return false;
			}
			else if (wAmount <= temp) {
				sum = temp - wAmount;
			}
			
			pstmt.setInt(1, sum);
			pstmt.setInt(2, user.getId());
			
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException s) {
			log.error("Exception in withdrawAmount thrown");
			s.getMessage();
			s.printStackTrace();
		} finally {
			log.warn("withdrawAmount - executed finally block");
		}log.warn("User could not withdraw");
		return false;
	}
}
