import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int Id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String Title = sc.nextLine();
                    System.out.print("Enter Book Author: ");
                    String Author = sc.nextLine();
                    library.addBook(new Book(Id, Title, Author));
                }
                case 2 -> library.listBooks();
                case 3 -> {
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    library.issueBook(issueId);
                }
                case 4 -> {
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                }
                case 5 -> System.out.println("Thank you for using the system!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
        sc.close();
    }
}