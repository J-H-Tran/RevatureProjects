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
import com.revature.model.Customer;
import com.revature.util.JDBCConnectionUtil;

public class CustomerImplementation implements CustomerDao {

	private static CustomerImplementation customerDao;
	final static Logger log = Logger.getLogger(CustomerImplementation.class);
	
	private CustomerImplementation() {
	}
	
	public static CustomerImplementation getCustomerDao(){
		if (customerDao == null){
			customerDao = new CustomerImplementation();
		}
		return customerDao;
	}

	@Override
	public boolean insertCustomer(Customer customer) {
		log.info("trying to register a customer");
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "insert into bank_user values (?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, customer.getId());
			ps.setInt(2, customer.getAuthz());
			ps.setString(3, customer.getFirstname());
			ps.setString(4, customer.getLastname());
			ps.setString(5, customer.getUsername());
			ps.setString(6, customer.getPassword());
			ps.setInt(7,  customer.getAccnum());
			
			if(ps.executeUpdate()>0) {
				return true;
			} 
		} catch (SQLException s) {
			log.error("catch block in insert Customer - Dao Implementation - occured");
			s.getMessage();
		} finally {
			log.warn("executed the finally block");
		}
		return false;
	}

	@Override
	public boolean insertCustomerProcedure(Customer customer) {
		log.info("trying to register a customer using stored procedure");
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String storeProcs = "call insert_customer(?,?,?,?)";
			CallableStatement cs = conn.prepareCall(storeProcs);
			cs.setString(2, customer.getFirstname());
			cs.setString(3, customer.getLastname());
			cs.setString(4, customer.getUsername());
			cs.setString(5, customer.getPassword());
			if(cs.executeUpdate()>0) {
				return true;
			} 
		} catch (SQLException s) {
			log.error("catch block in insert sustomer using store procedure");
			s.getMessage();
			s.printStackTrace();
		} finally {
			log.warn("insertCustomerProcedure - executed the finally block");
		}
		return false;
	}

	@Override
	public Customer getCustomer() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from customer where c_id = 42";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				log.info("did the while loop execute?");
				return new Customer(rs.getInt("C_ID"), 
						rs.getInt("auth"),
						rs.getString("C_FIRSTNAME"), 
						rs.getString("C_LASTNAME"),
						rs.getString("C_USERNAME"), 
						rs.getString("C_PASSWORD"),
						rs.getInt("accno"));
			}
		} catch (SQLException s) {
			log.error("catch block in getCustomer - Dao Implementation - occured");
			s.getMessage();
		} finally {
			log.warn("executed the finally block");
		}
		return new Customer();
	}

	@Override
	public List<Customer> getAllCustomers() {
		try (Connection conn = JDBCConnectionUtil.getConnection()) {
			String sql = "select * from customer";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			List<Customer> custList = new ArrayList<>();
			while (rs.next()) {
				log.info("did the while loop execute?");
				custList.add(new Customer(rs.getInt("C_ID"),
						rs.getInt("C_ID"),
						rs.getString("C_FIRSTNAME"), 
						rs.getString("C_LASTNAME"),
						rs.getString("C_USERNAME"),
						rs.getString("C_PASSWORD"),
						rs.getInt("C_ID"))
						);
			} 
			return custList;
		} catch (SQLException s) {
			log.error("catch block in getCustomer - Dao Implementation - occured");
			s.getMessage();
		} finally {
			log.warn("executed the finally block");
		}
		return new ArrayList<>();
	}
	
	
}
