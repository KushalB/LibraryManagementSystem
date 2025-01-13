public class Patron {
    private String id;
    private String name;
    private String email;
    private String membershipNumber;
    private java.util.Date joinDate;

    public Patron(String name, String email, String membershipNumber) {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.membershipNumber = membershipNumber;
        this.joinDate = new java.util.Date();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMembershipNumber() { return membershipNumber; }
    public void setMembershipNumber(String membershipNumber) { this.membershipNumber = membershipNumber; }
    public java.util.Date getJoinDate() { return joinDate; }

    @Override
    public String toString() {
        return String.format("Patron{id='%s', name='%s', email='%s', membershipNumber='%s', joinDate=%s}",
            id, name, email, membershipNumber, joinDate);
    }
}