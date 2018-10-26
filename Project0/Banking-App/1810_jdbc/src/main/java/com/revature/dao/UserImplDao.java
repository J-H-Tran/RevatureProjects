package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.revature.model.Balance;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserImplDao implements UserDao {
	private static UserImplDao usrDao;
	final static Logger log = Logger.getLogger(UserImplDao.class);
	
	private UserImplDao() {
	}
	public static UserImplDao getUserDao(){
		if (usrDao == null){
			usrDao = new UserImplDao();
		}
		return usrDao;
	}
	@Override
	public boolean checkUser(String username) {
		String usrStr = "";
		try { log.info("User attempted to perform a transation, being checked if account exists");
			Connection conn = ConnectionUtil.getConnection();
			String sql = "select * from bank_user where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) { usrStr = rs.getString("username");
			}
			if (usrStr.equals(username)) {
				log.info("User exists in database");
				return true;
			}
		}catch (Exception e) {
			log.error("Exception in checkUser thrown");
			e.printStackTrace();
		}log.warn("User does not exist");
		return false;
	}
	@Override
	public boolean insertUserProcedure(User user, Balance balance) { //inserts into bank_user table and balance_table
		log.info("Inserting user into database using stored procedure");
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			String storeProcs = "call add_user(?, ?, ?, ?, ?, ?, ?, ?)";
			CallableStatement cs = conn.prepareCall(storeProcs);
			
			cs.setString(1, user.getAuthz());
			cs.setString(2, user.getFirstname());
			cs.setString(3, user.getLastname());
			cs.setString(4, user.getUsername());
			cs.setString(5, user.getPassword());
			cs.setString(6, user.getApproval());
			cs.setInt(7, balance.getTypeId());
			cs.setInt(8, balance.getBalanceAmount());
			
			cs.executeUpdate();
			
			if(checkUser(user.getUsername())) {
				log.info("Insert into database successful");
				return true;
			} 
		} catch (SQLException s) {
			log.error("Exception in insertUserProcedure thrown");
			s.getMessage();
			s.printStackTrace();
		} finally {
			log.warn("insertUserProcedure - executed finally block");
		}log.warn("Insert failed");
		return false;
	}
	@Override
	public User getUser(String username) { 
		User user = new User();
		try (Connection conn = ConnectionUtil.getConnection()) {
			log.info("Retreiving user info");
			String sql = "select * from bank_user where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				log.info("did the while loop execute?");
				user = new User(rs.getInt("user_id"), rs.getString("authorize"), rs.getString("first_name"), rs.getString("last_name"), 
						rs.getString("username"), rs.getString("password"), rs.getInt("account_no"), rs.getString("approval_status"));
			}
			return user;
		} catch (SQLException s) {
			log.error("Exception in getUser thrown");
			s.getMessage();
		} finally {
			log.warn("getUser - executed finally block");
		}
		log.warn("Failed to get user info");
		return new User();
	}
	@Override
	public List<String> getAllUsers() {
		List<String> custList = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			log.info("Retreiving all users info");
			String sql = "select * from balance_table btable left join bank_user buser on btable.user_id=buser.user_id";
			//String sql = "select * from bank_user";
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			while (rs.next()) {
				custList.add(new User(
						rs.getInt("user_id"), rs.getString("authorize"), rs.getString("first_name"), rs.getString("last_name"), 
						rs.getString("username"), rs.getString("password"), rs.getInt("account_no"), rs.getString("approval_status")).toString()
						+new Balance(rs.getInt("user_id"), rs.getInt("balance_amt")).toString());
			} 
			return custList;
		} catch (SQLException s) {
			log.error("Exception in getAllUsers thrown");
			s.getMessage();
		} finally {
			log.warn("getAllUsers - executed finally block");
		}log.warn("Failed to get all users info");
		return custList;
	}
	@Override
	public boolean checkApproval(User user) { //Call SQL***********************
		if (user.getApproval().equals("no")) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public boolean verifyUser(int accNumber, User user) {
		
			try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "update bank_user set approval_status = ? where account_no = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, "yes");
				pstmt.setInt(2, accNumber);
				
				if (pstmt.executeUpdate() > 0) {
					log.info("executeUpdate to verify");
					return true;
				}
			} catch (SQLException s) {
				log.error("catch block in getUser - Dao Implementation - occured");
				s.getMessage();
			} finally {
				log.warn("executed the finally block");
			}
			return false;
	}
	@Override
	public boolean checkUserAuthorization(User user) { //Call SQL***********************
		if (user.getAuthz().equals("user")) {
			return true;
		}
		else {
			return false;
		}
	}
}
