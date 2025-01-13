import java.util.Date;
import java.util.Calendar;

public class LoanRecord {
    private String id;
    private String bookId;
    private String patronId;
    private Date checkoutDate;
    private Date dueDate;
    private Date returnDate;

    public LoanRecord(String bookId, String patronId) {
        this.id = java.util.UUID.randomUUID().toString();
        this.bookId = bookId;
        this.patronId = patronId;
        this.checkoutDate = new Date();
        // Set due date to 14 days from checkout
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.checkoutDate);
        calendar.add(Calendar.DAY_OF_MONTH, 14);
        this.dueDate = calendar.getTime();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getBookId() { return bookId; }
    public String getPatronId() { return patronId; }
    public Date getCheckoutDate() { return checkoutDate; }
    public Date getDueDate() { return dueDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return String.format("LoanRecord{id='%s', bookId='%s', patronId='%s', checkoutDate=%s, dueDate=%s, returnDate=%s}",
            id, bookId, patronId, checkoutDate, dueDate, returnDate);
    }
}