package com.revature.model;

import java.io.Serializable;

public class Balance implements Serializable{
	
	private static final long serialVersionUID = -531859940569610639L;
	private int balanceId;
	private int accountId;
	private int typeId;
	private int balanceAmount;
	
	public Balance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Balance(int balanceId, int accountId, int typeId, int balanceAmount) {
		super();
		this.balanceId = balanceId;
		this.accountId = accountId;
		this.typeId = typeId;
		this.balanceAmount = balanceAmount;
	}
	/*public Balance(int typeId, int balanceAmount) {
		super();
		this.typeId = typeId;
		this.balanceAmount = balanceAmount;
	}*/
	public Balance(int accountId, int balanceAmount) {
		super();
		this.accountId = accountId;
		this.balanceAmount = balanceAmount;
	}
	public int getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(int balanceId) {
		this.balanceId = balanceId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(int balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + balanceAmount;
		result = prime * result + balanceId;
		result = prime * result + typeId;
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
		Balance other = (Balance) obj;
		if (accountId != other.accountId)
			return false;
		if (balanceAmount != other.balanceAmount)
			return false;
		if (balanceId != other.balanceId)
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "[Account ID =" + accountId +  ", Balance =" + balanceAmount + "]";
	}
	
}
