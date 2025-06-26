public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable = true;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void display() {
        System.out.printf("%-5d %-25s %-20s %-10s\n", id, title, author, isAvailable ? "Available" : "Issued");
    }
}