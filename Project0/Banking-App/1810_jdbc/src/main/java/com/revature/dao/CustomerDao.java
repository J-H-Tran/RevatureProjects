package com.revature.dao;

import java.util.List;
import com.revature.model.Customer;

public interface CustomerDao {
	
	public boolean insertCustomer(Customer customer);
	public boolean insertCustomerProcedure(Customer customer);
	public Customer getCustomer();
	public List<Customer> getAllCustomers();

}
