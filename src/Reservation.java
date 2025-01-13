import java.util.Date;

public class Reservation {
    private String id;
    private String bookId;
    private String patronId;
    private Date reservationDate;
    private ReservationStatus status;

    public Reservation(String bookId, String patronId) {
        this.id = java.util.UUID.randomUUID().toString();
        this.bookId = bookId;
        this.patronId = patronId;
        this.reservationDate = new Date();
        this.status = ReservationStatus.PENDING;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getBookId() { return bookId; }
    public String getPatronId() { return patronId; }
    public Date getReservationDate() { return reservationDate; }
    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Reservation{id='%s', bookId='%s', patronId='%s', reservationDate=%s, status=%s}",
            id, bookId, patronId, reservationDate, status);
    }
}