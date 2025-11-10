import java.util.Scanner;

class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie next, prev;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieList {
    Movie head, tail;

    void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
            return;
        }
        newMovie.next = head;
        head.prev = newMovie;
        head = newMovie;
    }

    void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
            return;
        }
        tail.next = newMovie;
        newMovie.prev = tail;
        tail = newMovie;
    }

    void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos <= 1 || head == null) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        Movie temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++)
            temp = temp.next;
        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
            return;
        }
        newMovie.next = temp.next;
        newMovie.prev = temp;
        temp.next.prev = newMovie;
        temp.next = newMovie;
    }

    void removeByTitle(String title) {
        if (head == null) return;
        if (head.title.equalsIgnoreCase(title)) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
            return;
        }
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title))
            temp = temp.next;
        if (temp == null) return;
        if (temp.next != null) temp.next.prev = temp.prev;
        else tail = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;
    }

    void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movie found by director " + director);
    }

    void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No movie found with rating " + rating);
    }

    void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
    }

    void displayForward() {
        Movie temp = head;
        if (temp == null) {
            System.out.println("No movies to display.");
            return;
        }
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Movie temp = tail;
        if (temp == null) {
            System.out.println("No movies to display.");
            return;
        }
        while (temp != null) {
            System.out.println(temp.title + " | " + temp.director + " | " + temp.year + " | " + temp.rating);
            temp = temp.prev;
        }
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieList list = new MovieList();
        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by Title\n5. Search by Director\n6. Search by Rating\n7. Update Rating\n8. Display Forward\n9. Display Reverse\n10. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Title: "); String t1 = sc.next();
                    System.out.print("Director: "); String d1 = sc.next();
                    System.out.print("Year: "); int y1 = sc.nextInt();
                    System.out.print("Rating: "); double r1 = sc.nextDouble();
                    list.addAtBeginning(t1, d1, y1, r1);
                    break;
                case 2:
                    System.out.print("Title: "); String t2 = sc.next();
                    System.out.print("Director: "); String d2 = sc.next();
                    System.out.print("Year: "); int y2 = sc.nextInt();
                    System.out.print("Rating: "); double r2 = sc.nextDouble();
                    list.addAtEnd(t2, d2, y2, r2);
                    break;
                case 3:
                    System.out.print("Position: "); int pos = sc.nextInt();
                    System.out.print("Title: "); String t3 = sc.next();
                    System.out.print("Director: "); String d3 = sc.next();
                    System.out.print("Year: "); int y3 = sc.nextInt();
                    System.out.print("Rating: "); double r3 = sc.nextDouble();
                    list.addAtPosition(pos, t3, d3, y3, r3);
                    break;
                case 4:
                    System.out.print("Enter title to remove: "); String rt = sc.next();
                    list.removeByTitle(rt);
                    break;
                case 5:
                    System.out.print("Enter director: "); String sd = sc.next();
                    list.searchByDirector(sd);
                    break;
                case 6:
                    System.out.print("Enter rating: "); double sr = sc.nextDouble();
                    list.searchByRating(sr);
                    break;
                case 7:
                    System.out.print("Enter title to update: "); String ut = sc.next();
                    System.out.print("Enter new rating: "); double nr = sc.nextDouble();
                    list.updateRating(ut, nr);
                    break;
                case 8:
                    list.displayForward();
                    break;
                case 9:
                    list.displayReverse();
                    break;
                case 10:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
