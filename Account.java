package model;

public abstract class Account {
	
	private static int idCounter = 1;
	private int accountID;
	private String fullName;
	private String username;
	private String password;
	
	public Account(String fullName, String username, String password) {
		this.accountID = idCounter++;
		this.fullName = fullName;
		this.username = username;
		this.password = password;
	}
	
	public int getAccountID() { return accountID; }
	public String getFullName() { return fullName; }
	public String getUsername() { return username; }
	public String getPassword() { return password; }
}