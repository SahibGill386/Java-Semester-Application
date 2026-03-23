package model;

import java.util.ArrayList;
import java.util.List;

public class Patron extends Account {
	
	private List<LoanRecord> currentLoans;
	private double totalFines;
	
	public Patron(String fullName, String username, String password) {
		super(fullName, username, password);
		currentLoans = new ArrayList<>();
		totalFines = 0.0;
	}
	
	public boolean canBorrow() {
		return currentLoans.size() < 5 && totalFines == 0;
	}
	
	public void payFine(double amount) {
		totalFines -= amount;
		if (totalFines < 0) { totalFines = 0; }
	}
	
	public void addFine(double amount) {
		if (amount > 0) { totalFines += amount; }
	}
	
	public double getTotalFines() { return totalFines; }
	public List<LoanRecord> getCurrentLoans() { return currentLoans; }
	public void addLoan(LoanRecord loan) { currentLoans.add(loan); }
	public void returnLoan(LoanRecord loan) { currentLoans.remove(loan); }
}