import java.util.List;
import java.util.Scanner;

public class Main {
    private static LibraryService libraryService = new LibraryService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 3:
                    addPatron();
                    break;
                case 4:
                    checkoutBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    reserveBook();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. Add Book");
        System.out.println("2. Search Books");
        System.out.println("3. Add Patron");
        System.out.println("4. Checkout Book");
        System.out.println("5. Return Book");
        System.out.println("6. Reserve Book");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter branch ID: ");
        String branchId = scanner.nextLine();

        Book book = libraryService.addBook(title, author, isbn, year, branchId);
        System.out.println("Book added successfully: " + book);
    }

    private static void searchBooks() {
        System.out.print("Enter search term: ");
        String query = scanner.nextLine();
        List<Book> results = libraryService.searchBooks(query);
        
        if (results.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Search results:");
            results.forEach(System.out::println);
        }
    }

    private static void addPatron() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter membership number: ");
        String membershipNumber = scanner.nextLine();

        Patron patron = libraryService.addPatron(name, email, membershipNumber);
        System.out.println("Patron added successfully: " + patron);
    }

    private static void checkoutBook() {
        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter patron ID: ");
        String patronId = scanner.nextLine();

        try {
            LoanRecord loan = libraryService.checkoutBook(bookId, patronId);
            System.out.println("Book checked out successfully: " + loan);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnBook() {
        System.out.print("Enter loan ID: ");
        String loanId = scanner.nextLine();

        try {
            libraryService.returnBook(loanId);
            System.out.println("Book returned successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void reserveBook() {
        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter patron ID: ");
        String patronId = scanner.nextLine();

        try {
            Reservation reservation = libraryService.reserveBook(bookId, patronId);
            System.out.println("Book reserved successfully: " + reservation);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}