import java.util.Scanner;

class Book {
    String title;
    String author;
    String genre;
    int id;
    String status;
    Book next, prev;

    Book(String title, String author, String genre, int id, String status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.id = id;
        this.status = status;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    Book head, tail;

    void addAtBeginning(String title, String author, String genre, int id, String status) {
        Book newBook = new Book(title, author, genre, id, status);
        if (head == null) {
            head = tail = newBook;
            return;
        }
        newBook.next = head;
        head.prev = newBook;
        head = newBook;
    }

    void addAtEnd(String title, String author, String genre, int id, String status) {
        Book newBook = new Book(title, author, genre, id, status);
        if (tail == null) {
            head = tail = newBook;
            return;
        }
        tail.next = newBook;
        newBook.prev = tail;
        tail = newBook;
    }

    void addAtPosition(int pos, String title, String author, String genre, int id, String status) {
        if (pos <= 1 || head == null) {
            addAtBeginning(title, author, genre, id, status);
            return;
        }
        Book newBook = new Book(title, author, genre, id, status);
        Book temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++)
            temp = temp.next;
        if (temp == null || temp.next == null) {
            addAtEnd(title, author, genre, id, status);
            return;
        }
        newBook.next = temp.next;
        newBook.prev = temp;
        temp.next.prev = newBook;
        temp.next = newBook;
    }

    void removeById(int id) {
        if (head == null) return;
        if (head.id == id) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
            return;
        }
        Book temp = head;
        while (temp != null && temp.id != id)
            temp = temp.next;
        if (temp == null) return;
        if (temp.next != null) temp.next.prev = temp.prev;
        else tail = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
    }

    void searchByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                System.out.println("Found -> ID: " + temp.id + ", Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Status: " + temp.status);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book not found.");
    }

    void searchByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                System.out.println("Found -> ID: " + temp.id + ", Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Status: " + temp.status);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Book not found.");
    }

    void updateStatus(int id, String status) {
        Book temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.status = status;
                return;
            }
            temp = temp.next;
        }
    }

    void displayForward() {
        if (head == null) {
            System.out.println("No books in library.");
            return;
        }
        Book temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.id + ", Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Status: " + temp.status);
            temp = temp.next;
        }
    }

    void displayReverse() {
        if (tail == null) {
            System.out.println("No books in library.");
            return;
        }
        Book temp = tail;
        while (temp != null) {
            System.out.println("ID: " + temp.id + ", Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Status: " + temp.status);
            temp = temp.prev;
        }
    }

    void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total Books: " + count);
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by ID\n5. Search by Title\n6. Search by Author\n7. Update Status\n8. Display Forward\n9. Display Reverse\n10. Count Books\n11. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Title: "); String t1 = sc.next();
                    System.out.print("Author: "); String a1 = sc.next();
                    System.out.print("Genre: "); String g1 = sc.next();
                    System.out.print("ID: "); int i1 = sc.nextInt();
                    System.out.print("Status: "); String s1 = sc.next();
                    lib.addAtBeginning(t1, a1, g1, i1, s1);
                    break;
                case 2:
                    System.out.print("Title: "); String t2 = sc.next();
                    System.out.print("Author: "); String a2 = sc.next();
                    System.out.print("Genre: "); String g2 = sc.next();
                    System.out.print("ID: "); int i2 = sc.nextInt();
                    System.out.print("Status: "); String s2 = sc.next();
                    lib.addAtEnd(t2, a2, g2, i2, s2);
                    break;
                case 3:
                    System.out.print("Position: "); int pos = sc.nextInt();
                    System.out.print("Title: "); String t3 = sc.next();
                    System.out.print("Author: "); String a3 = sc.next();
                    System.out.print("Genre: "); String g3 = sc.next();
                    System.out.print("ID: "); int i3 = sc.nextInt();
                    System.out.print("Status: "); String s3 = sc.next();
                    lib.addAtPosition(pos, t3, a3, g3, i3, s3);
                    break;
                case 4:
                    System.out.print("Enter ID: "); int rid = sc.nextInt();
                    lib.removeById(rid);
                    break;
                case 5:
                    System.out.print("Enter Title: "); String st = sc.next();
                    lib.searchByTitle(st);
                    break;
                case 6:
                    System.out.print("Enter Author: "); String sa = sc.next();
                    lib.searchByAuthor(sa);
                    break;
                case 7:
                    System.out.print("Enter ID: "); int uid = sc.nextInt();
                    System.out.print("Enter new Status: "); String us = sc.next();
                    lib.updateStatus(uid, us);
                    break;
                case 8:
                    lib.displayForward();
                    break;
                case 9:
                    lib.displayReverse();
                    break;
                case 10:
                    lib.countBooks();
                    break;
                case 11:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
