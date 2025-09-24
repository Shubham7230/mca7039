import java.util.ArrayList;

class Book {
    protected String title;
    protected int publicationYear;
    
    public Book(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
    }
    
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Publication Year: " + publicationYear);
    }
}

class Author extends Book {
    private String name;
    private String bio;
    private ArrayList<Book> authoredBooks;
    
    public Author(String name, String bio, String title, int publicationYear) {
        super(title, publicationYear);
        this.name = name;
        this.bio = bio;
        this.authoredBooks = new ArrayList<>();
    }
    
    public void addAuthoredBook(Book book) {
        authoredBooks.add(book);
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Author Name: " + name);
        System.out.println("Author Bio: " + bio);
        System.out.println("--- Authored Books ---");
        for (Book book : authoredBooks) {
            System.out.println("Title: " + book.title + " (" + book.publicationYear + ")");
        }
    }
    
    public void displayAuthorDetails() {
        System.out.println("Author: " + name);
        System.out.println("Bio: " + bio);
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Book book1 = new Book("The Great Gatsby", 1925);
        Book book2 = new Book("To Kill a Mockingbird", 1960);
        Book book3 = new Book("1984", 1949);
        
        Author author1 = new Author("F. Scott Fitzgerald", "American novelist and short story writer", "The Great Gatsby", 1925);
        Author author2 = new Author("Harper Lee", "American novelist", "To Kill a Mockingbird", 1960);
        
        author1.addAuthoredBook(book1);
        author2.addAuthoredBook(book2);
        author2.addAuthoredBook(book3);
        
        System.out.println("=== Book Information ===");
        book1.displayInfo();
        System.out.println();
        
        book2.displayInfo();
        System.out.println();
        
        System.out.println("=== Author Information ===");
        author1.displayInfo();
        System.out.println();
        
        author2.displayInfo();
        System.out.println();
        
        System.out.println("=== Author Details ===");
        author1.displayAuthorDetails();
        System.out.println();
        
        author2.displayAuthorDetails();
    }
}