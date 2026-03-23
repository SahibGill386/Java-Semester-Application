package model;

import java.time.LocalDate;

public class LoanRecord {
	
	private Patron patron;
	private Book book;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	
	public LoanRecord(Patron patron, Book book, LocalDate checkoutDate, LocalDate dueDate) {
	this.patron = patron;
	this.book = book;
	this.checkoutDate = LocalDate.now();
	this.dueDate = checkoutDate.plusDays(14);
}

public Patron getPatron() {
	return patron;
}
	
public Book getBook() {
	return book;
}

public LocalDate getCheckoutDate() {
	return checkoutDate;
}

public LocalDate getDueDate() {
	return dueDate;
}

public void setCheckoutDate(LocalDate checkoutDate) {
	this.checkoutDate = checkoutDate;
}

public void setDueDate(LocalDate dueDate) {
	this.dueDate = dueDate;
}

}
