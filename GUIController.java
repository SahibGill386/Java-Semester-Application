package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import java.util.List;

public class GUIController {

    @FXML private Label statusLabel;
    @FXML private TextField searchField;
    @FXML private TextArea displayArea;
    
    @FXML private TextField userField;
    @FXML private PasswordField passField;
    
    @FXML private TextField patronIdField;
    @FXML private TextField bookIdField;

    private LibrarySystem system = new LibrarySystem();

    @FXML
    public void handleSearchTitle() {
        String query = searchField.getText();
        List<Book> results = system.searchByTitle(query);
        displayResults(results);
    }

    @FXML
    public void handleSearchAuthor() {
        String query = searchField.getText();
        List<Book> results = system.searchByAuthor(query);
        displayResults(results);
    }
    
    @FXML
    public void handleViewAll() {
    	List<Book> results = system.getDatabase().getAllBooks();
    	displayResults(results);
    }

    private void displayResults(List<Book> results) {
        displayArea.clear();
        if (results.isEmpty()) {
            displayArea.setText("No books found.");
        } else {
            for (Book b : results) {
                String status = b.isAvailable() ? "Available" : "Rented";
                displayArea.appendText("ID: " + b.getID() + " | " + b.getTitle() + " by " + b.getAuthor() + " [" + status + "]\n");
            }
        }
    }

    @FXML
    public void handleLogin() {
        String username = userField.getText();
        String password = passField.getText();
        if (system.login(username, password)) {
            statusLabel.setText("Logged in as: " + username);
        } else {
            statusLabel.setText("Invalid login credentials.");
        }
    }

    @FXML
    public void handleLogout() {
        system.logout();
        statusLabel.setText("Successfully logged out.");
        userField.clear();
        passField.clear();
    }

    @FXML
    public void handleCheckout() {
        if (!system.isAdmin()) {
            statusLabel.setText("Error: You must log in as a Librarian first.");
            return;
        }
        
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            Book book = system.searchByID(bookId);
            
            // Note: In a real system you'd search for the Patron, here we simulate finding one
            Patron patron = null;
            for(Account acc : system.getDatabase().getAllAccounts()) {
            	if(acc instanceof Patron && acc.getUsername().equals(patronIdField.getText())) {
            		patron = (Patron) acc;
            		break;
            	}
            }

            if (book == null || patron == null) {
                statusLabel.setText("Error: Invalid Book ID or Patron Username.");
                return;
            }

            if (system.checkoutBook(patron, book)) {
                statusLabel.setText("Success: Book checked out to " + patron.getUsername());
                handleViewAll(); // Refresh inventory view
            } else {
                statusLabel.setText("Failed: Book unavailable, or Patron has fines/exceeded 5 books.");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Book ID must be a number.");
        }
    }

    @FXML
    public void handleReturn() {
        if (!system.isAdmin()) {
            statusLabel.setText("Error: You must log in as a Librarian first.");
            return;
        }
        
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            Book book = system.searchByID(bookId);
            
            Patron patron = null;
            for(Account acc : system.getDatabase().getAllAccounts()) {
            	if(acc instanceof Patron && acc.getUsername().equals(patronIdField.getText())) {
            		patron = (Patron) acc;
            		break;
            	}
            }

            if (book == null || patron == null) {
                statusLabel.setText("Error: Invalid Book ID or Patron Username.");
                return;
            }

            if (system.returnBook(patron, book)) {
                statusLabel.setText("Success: Book returned by " + patron.getUsername());
                handleViewAll(); // Refresh inventory view
            } else {
                statusLabel.setText("Failed: Could not process return.");
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Book ID must be a number.");
        }
    }
}