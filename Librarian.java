package model;

import java.time.LocalDate;
import repository.LibraryDatabase;
import service.FineCalculator;

public class Librarian extends Account {
	
	public Librarian(String fullName, String username, String password) {
		super(fullName, username, password);
	}
	
	public boolean processCheckout(Patron patron, Book book) {
		if (!book.isAvailable() || !patron.canBorrow()) {
			return false;
		}
		LoanRecord loan = new LoanRecord(patron, book, LocalDate.now(), LocalDate.now().plusDays(14));
		book.setRented(true);
		patron.addLoan(loan);
		return true;
	}
	
	public boolean processReturn(Patron patron, Book book, FineCalculator calculator) {
		LoanRecord loanToReturn = null;
		for (LoanRecord loan : patron.getCurrentLoans()) {
			if (loan.getBook().equals(book)) {
				loanToReturn = loan;
				break;
			}
		}
		if (loanToReturn == null) { return false; }
		if (calculator.isOverdue(loanToReturn)) {
			double fine = calculator.calculateFine(loanToReturn);
			patron.payFine(fine);
		}
		book.setRented(false);
		patron.returnLoan(loanToReturn);
		return true;
	}
	
	public void modifyInventory(LibraryDatabase database, Book book, boolean add) {
		if (add) {
			database.addBook(book);
		} else {
			database.removeBook(book);
		}
	}
}