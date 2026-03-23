package service;

import model.LoanRecord;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {
	
	private double dailyRate;
	
	public FineCalculator(double dailyRate) {
		this.dailyRate = dailyRate;
	}
	
	public boolean isOverdue(LoanRecord loan) {
		LocalDate today = LocalDate.now();
		return today.isAfter(loan.getDueDate());
	}
	
	public double calculateFine(LoanRecord loan) {
		if (!isOverdue(loan)) { return 0.0; }
		LocalDate today = LocalDate.now();
		long overdueDays = ChronoUnit.DAYS.between(loan.getDueDate(), today);
		return overdueDays * dailyRate;
	}
	
	public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }
	public double getDailyRate() { return dailyRate; }
}