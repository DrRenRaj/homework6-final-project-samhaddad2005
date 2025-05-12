/* Sam Haddad
 * 5/9/2025
 * Assignment 6
 */

package hw6;
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;
    
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true; // setting available to true helps with later methods
    }
    // Getters and setters to be used in the next class
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getISBN() {
        return ISBN;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
   
    public String toString() {  // structuring it in a way that would look readable as it will be put into an array later
        return " (Title: " + title + ", Author: " + author + ", ISBN: " + ISBN + 
               ", Available: " + (isAvailable ? "Yes" : "No") + ")"; 
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    
    public void addBook(Book book) { // method to add books
        for (Book existingBook : books) { // enhanced for loop to go through the array
            if (existingBook.getISBN().equals(book.getISBN())) { //check if any of the books have the same ISBN as the new one.
            System.out.println("ISBN mismatch. Book has already been added."); 
            return;
            }
        }
        
        books.add(book); // if there is no matching ISBN's this adds the book
        System.out.println("Book added successfully.");
    }
    public void removeBook(String ISBN) { // method to remove book
        for (Book book : books) { // enhanced for loop to go through the array
            if (book.getISBN().equals(ISBN)) { // checks if the books exist
                books.remove(book); //if they do remove them
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }
    public void displayAllBooks() { //prints the books which contains title, author, ISBN, and availability
		System.out.println("Available books: " + books);
	}
    public void searchByTitle(String title) {
    	boolean found = false; // In order to have the loop stop if found
    	for (Book book : books) { // enhanced loop to go through the array
    		if (book.getTitle().equals(title)) { // Checking if the title exists
    			System.out.println("Found the book with the title " + title);
    			found = true; //
    		}
    	}
    		if (!found) { // if the book was not found by its title its prints:
    			System.out.println("Book not found.");
    		}
    }

	public void searchByAuthor(String author) {
	boolean found = false;// In order to have the loop stop if found
	for (Book book : books) {  // enhanced loop to go through the array
	    if (book.getAuthor().equals(author)) { // check for author by matching with array names of authors
	        System.out.println(book);
	        found = true;
	    }
	}
	if (!found) {// if the book was not found by its author its prints:
	    System.out.println("Book not found.");
	}
	}
	
	public void checkOutBook(String ISBN) {
	for (Book book : books) { // enhanced loop to go through the array
	    if (book.getISBN().equals(ISBN)) {// check if book exists in array
	        if (book.isAvailable()) { // if it is available
	            book.setAvailable(false); // make it not available by checking it out
	            System.out.println("Book checked out successfully.");
	            return;
	        } else {
	            System.out.println("Book is already checked out.");
	        }
	    }
	}
	System.out.println("Book not found."); // if it doesn't even exits
	}
	
	public void returnBook(String ISBN) {
	for (Book book : books) { // enhanced loop to go through the array
	    if (book.getISBN().equals(ISBN)) {
	        if (!book.isAvailable()) { // if its not available
	            book.setAvailable(true); // make it available by returning it
	            System.out.println("Book returned successfully.");
	            return;
	        } else {
	            System.out.println("Book is already available.");
	        }
	    }
	}
System.out.println("Book not found."); // if it doesn't even exits
}
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int x = 5; //breaking out the loop
        while (x>0) { //menu
            System.out.println("\n\nWelcome to the Denver Library. How would you like to proceed?");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Search by Title");
            System.out.println("5. Search by Author");
            System.out.println("6. Check Out Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try { // makes sure number is between 1 and 8
                choice = sc.nextInt();
                sc.nextLine(); 
            } catch (Exception e) { 
                System.out.println("Please enter a valid number.");
                sc.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1: // adding a book
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    Book book = new Book(title, author, isbn);
                    library.addBook(book);
                    break;

                case 2: //removing a book
                    System.out.print("Enter ISBN to remove: ");
                    String removeIsbn = sc.nextLine();
                    library.removeBook(removeIsbn);
                    break;

                case 3: //displaying books in the array
                    library.displayAllBooks();
                    break;

                case 4: // searching by title
                    System.out.print("Enter title to search: ");
                    String searchTitle = sc.nextLine();
                    library.searchByTitle(searchTitle);
                    break;

                case 5: // searching by author
                    System.out.print("Enter author to search: ");
                    String searchAuthor = sc.nextLine();
                    library.searchByAuthor(searchAuthor);
                    break;

                case 6: // checking a book out
                    System.out.print("Enter ISBN to check out: ");
                    String checkoutIsbn = sc.nextLine();
                    library.checkOutBook(checkoutIsbn);
                    break;

                case 7: // returning a checked out book
                    System.out.print("Enter ISBN to return: ");
                    String returnIsbn = sc.nextLine();
                    library.returnBook(returnIsbn);
                    break;

                case 8: // exit
                    System.out.println("Thank you for using the Denver Library System!");
                    x = 0;
                    break;
                    

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        sc.close();
    }
}
