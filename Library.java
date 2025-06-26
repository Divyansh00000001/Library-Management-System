import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
        } else {
            System.out.printf("%-5s %-25s %-20s %-10s\n", "ID", "Title", "Author", "Status");
            System.out.println("--------------------------------------------------------------");
            for (Book b : books) {
                b.display();
            }
        }
    }

    public void issueBook(int Id) {
        for (Book b : books) {
            if (b.getId() == Id) {
                if (b.isAvailable()) {
                    b.setAvailable(false);
                    System.out.println("Book issued successfully.");
                } else {
                    System.out.println("Book is already issued.");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }

    public void returnBook(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                if (!b.isAvailable()) {
                    b.setAvailable(true);
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book ID not found.");
    }
}