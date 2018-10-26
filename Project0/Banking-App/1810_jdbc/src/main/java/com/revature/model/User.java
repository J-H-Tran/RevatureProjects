package com.revature.model;
import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 212101819135608750L;
	
	private int id;
	private String authz;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private int accnum;
	private String approval;
	private int balance;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String authz, String firstname, String lastname, String username, String password,
			int accnum, String approval) {
		super();
		this.id = id;
		this.authz = authz;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.accnum = accnum;
		this.approval = approval;
	}
	public User(String authz, String firstname, String lastname, String username, String password, String approval) {//stored procedure
		super();
		this.authz = authz;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.approval = approval;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthz() {
		return authz;
	}
	public void setAuthz(String authz) {
		this.authz = authz;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccnum() {
		return accnum;
	}
	public void setAccnum(int accnum) {
		this.accnum = accnum;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accnum;
		result = prime * result + ((approval == null) ? 0 : approval.hashCode());
		result = prime * result + ((authz == null) ? 0 : authz.hashCode());
		result = prime * result + balance;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accnum != other.accnum)
			return false;
		if (approval == null) {
			if (other.approval != null)
				return false;
		} else if (!approval.equals(other.approval))
			return false;
		if (authz == null) {
			if (other.authz != null)
				return false;
		} else if (!authz.equals(other.authz))
			return false;
		if (balance != other.balance)
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "\nUser [id=" + id + ", authz=" + authz + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", username=" + username + ", password=" + password + ", accnum=" + accnum + ", approval=" + approval
				+ ", balance=" + balance + "]";
	}
	
}
