

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Sample Data
        library.addBook(new Book(1, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book(2, "Java Basics", "James Gosling"));
        library.addUser(new User(101, "Alice"));
        library.addUser(new User(102, "Bob"));

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Show all books");
            System.out.println("2. Add Book");
            System.out.println("3. Show all users");
            System.out.println("4. Add Users");
            System.out.println("5. Issue book");
            System.out.println("6. Return book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.showBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter Book title: ");
                    String name = sc.next();
                    System.out.print("Enter Book author: ");
                    String author = sc.next();
                    sc.nextLine();
                    library.addBook(new Book(bookId,name,author));
                    break;
                case 3:

                    library.showUsers();
                    break;
                case 4:
                    System.out.print("Enter user ID: ");
                    int studid = sc.nextInt();
                    System.out.print("Enter username: ");
                    String studname = sc.next();
                    sc.nextLine();
                    library.addUser(new User(studid,studname));
                    break;
                case 5:
                    System.out.print("Enter Book ID: ");
                    int Id = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    library.issueBook(Id, userId);
                    break;
                case 6:
                    System.out.print("Enter Book ID: ");
                    bookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    userId = sc.nextInt();
                    library.returnBook(bookId, userId);
                    break;
                case 7:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}


// Book.java
class Book {
    private int id;
    private String title;
    private String author;
    private boolean issued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return issued; }

    public void issue() { 
        issued = true;
     }
    public void returnBook() {
         issued = false; 
        }

    @Override
    public String toString() {
        return id + " | " + title + " by " + author + (issued ? " [Issued]" : " [Available]");
    }
}


// Library.java


 class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Add book
    public void addBook(Book book) {
        books.add(book);
    }

    // Add user
    public void addUser(User user) {
        users.add(user);
    }

    // Show all books
    public void showBooks() {
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // Show all users
    public void showUsers() {
        for (User u : users) {
            System.out.println(u);
        }
    }

    // Issue book
    public void issueBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null || user == null) {
            System.out.println("Book or User not found!");
            return;
        }

        if (!book.isIssued()) {
            book.issue();
            user.issueBook(book);
            System.out.println("Book issued to " + user.getName());
        } else {
            System.out.println("Book is already issued!");
        }
    }

    // Return book
    public void returnBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book == null || user == null) {
            System.out.println("Book or User not found!");
            return;
        }

        if (book.isIssued()) {
            book.returnBook();
            user.returnBook(book);
            System.out.println("Book returned by " + user.getName());
        } else {
            System.out.println("Book was not issued!");
        }
    }

    
    private Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    private User findUser(int id) {
        for (User u : users) {
            if (u.getId() == id) return u;
        }
        return null;
    }
}




class User {
    private int id;
    private String name;
    private List<Book> issuedBooks;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Book> getIssuedBooks() { return issuedBooks; }

    public void issueBook(Book book) {
        issuedBooks.add(book);
    }

    public void returnBook(Book book) {
        issuedBooks.remove(book);
    }

    @Override
    public String toString() {
        return id + " | " + name + " | Issued books: " + issuedBooks.size();
    }
}


