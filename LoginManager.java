package service;

import model.Account;
import model.Librarian;
import repository.LibraryDatabase;

public class LoginManager {
	
	private LibraryDatabase database;
	private Account currentUser;
	
	public LoginManager(LibraryDatabase database) {
		this.database = database;
		this.currentUser = null;
	}
	
	public boolean authenticate(String username, String password) {
		for (Account account : database.getAllAccounts()) {
			if (account.getUsername().equals(username) && 
				account.getPassword().equals(password)) {
				currentUser = account;
				return true;
			}
		}
		
		return false;
	}

	public void logout() {
		currentUser = null;
	}
	
	public Account getCurrentUser() {
		return currentUser;
	}
	
	public boolean isLoggedIn() {
		return currentUser != null;
	}
	
	public boolean isAdmin() {
		return currentUser instanceof Librarian;
	}
	
}
