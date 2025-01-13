public class Branch {
    private String id;
    private String name;
    private String address;

    public Branch(String name, String address) {
        this.id = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return String.format("Branch{id='%s', name='%s', address='%s'}", id, name, address);
    }
}