package model;

public class Book {
	
	public static int idCounter = 1;
	
	private String title;
	private String author;
	private int id;
	private boolean isRented;
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.isRented = false;
		this.id = idCounter++;
	}
	
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	public int getID() { return id; }
	public boolean isRented() { return isRented; }
	public void setRented(boolean isRented) { this.isRented = isRented; }
	public boolean isAvailable() { return !isRented; }
}