package com.revature.service;

import java.util.List;

import com.revature.dao.CustomerImplementation;
import com.revature.model.Customer;

public class CustomerService {

	private static CustomerService custService;
	private CustomerService() {
	}
	
	public static CustomerService getCustomerService() {
		if(custService == null) {
			custService = new CustomerService();
		}
		return custService;
	}
	
	public Customer getCustomerDetails(){
		return CustomerImplementation.getCustomerDao().getCustomer();
	}
	
	public List<Customer> listAllCustomers(){
		return CustomerImplementation.getCustomerDao().getAllCustomers();
	}
	
	public boolean registerCustomer(Customer customer) {
		return CustomerImplementation.getCustomerDao().insertCustomer(customer);
	}
	
	public boolean registerCustomerProcs(Customer customer) {
		return CustomerImplementation.getCustomerDao().insertCustomerProcedure(customer);
	}
}
