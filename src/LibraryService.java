import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private Map<String, Book> books;
    private Map<String, Patron> patrons;
    private Map<String, LoanRecord> loans;
    private Map<String, Reservation> reservations;
    private Map<String, Branch> branches;

    public LibraryService() {
        this.books = new HashMap<>();
        this.patrons = new HashMap<>();
        this.loans = new HashMap<>();
        this.reservations = new HashMap<>();
        this.branches = new HashMap<>();
    }

    // Book Management
    public Book addBook(String title, String author, String isbn, int publicationYear, String branchId) {
        Book book = new Book(title, author, isbn, publicationYear, branchId);
        books.put(book.getId(), book);
        return book;
    }

    public List<Book> searchBooks(String query) {
        String searchTerm = query.toLowerCase();
        return books.values().stream()
            .filter(book -> 
                book.getTitle().toLowerCase().contains(searchTerm) ||
                book.getAuthor().toLowerCase().contains(searchTerm) ||
                book.getIsbn().contains(searchTerm))
            .collect(Collectors.toList());
    }

    // Patron Management
    public Patron addPatron(String name, String email, String membershipNumber) {
        Patron patron = new Patron(name, email, membershipNumber);
        patrons.put(patron.getId(), patron);
        return patron;
    }

    // Lending Process
    public LoanRecord checkoutBook(String bookId, String patronId) {
        Book book = books.get(bookId);
        Patron patron = patrons.get(patronId);

        if (book == null || patron == null) {
            throw new IllegalArgumentException("Book or patron not found");
        }

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available");
        }

        LoanRecord loan = new LoanRecord(bookId, patronId);
        loans.put(loan.getId(), loan);
        book.setAvailable(false);

        return loan;
    }

    public void returnBook(String loanId) {
        LoanRecord loan = loans.get(loanId);
        if (loan == null) {
            throw new IllegalArgumentException("Loan not found");
        }

        Book book = books.get(loan.getBookId());
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        loan.setReturnDate(new Date());
        book.setAvailable(true);

        processReservations(book.getId());
    }

    // Reservation System
    public Reservation reserveBook(String bookId, String patronId) {
        Book book = books.get(bookId);
        Patron patron = patrons.get(patronId);

        if (book == null || patron == null) {
            throw new IllegalArgumentException("Book or patron not found");
        }

        Reservation reservation = new Reservation(bookId, patronId);
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    private void processReservations(String bookId) {
        List<Reservation> pendingReservations = reservations.values().stream()
            .filter(r -> r.getBookId().equals(bookId) && r.getStatus() == ReservationStatus.PENDING)
            .sorted(Comparator.comparing(Reservation::getReservationDate))
            .collect(Collectors.toList());

        if (!pendingReservations.isEmpty()) {
            Reservation nextReservation = pendingReservations.get(0);
            nextReservation.setStatus(ReservationStatus.FULFILLED);
            // In a real system, we would notify the patron here
        }
    }
}