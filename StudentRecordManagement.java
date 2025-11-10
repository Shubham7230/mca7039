import java.util.Scanner;

class Student {
    int roll;
    String name;
    int age;
    String grade;
    Student next;

    Student(int roll, String name, int age, String grade) {
        this.roll = roll;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList {
    Student head;

    void addAtBeginning(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    void addAtEnd(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newStudent;
    }

    void addAtPosition(int pos, int roll, String name, int age, String grade) {
        if (pos <= 1) {
            addAtBeginning(roll, name, age, grade);
            return;
        }
        Student newStudent = new Student(roll, name, age, grade);
        Student temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++)
            temp = temp.next;
        if (temp == null)
            addAtEnd(roll, name, age, grade);
        else {
            newStudent.next = temp.next;
            temp.next = newStudent;
        }
    }

    void deleteByRoll(int roll) {
        if (head == null)
            return;
        if (head.roll == roll) {
            head = head.next;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.roll != roll)
            temp = temp.next;
        if (temp.next != null)
            temp.next = temp.next.next;
    }

    Student searchByRoll(int roll) {
        Student temp = head;
        while (temp != null) {
            if (temp.roll == roll)
                return temp;
            temp = temp.next;
        }
        return null;
    }

    void updateGrade(int roll, String newGrade) {
        Student student = searchByRoll(roll);
        if (student != null)
            student.grade = newGrade;
    }

    void displayAll() {
        Student temp = head;
        if (temp == null) {
            System.out.println("No records found.");
            return;
        }
        while (temp != null) {
            System.out.println("Roll: " + temp.roll + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList list = new StudentList();
        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Delete by Roll\n5. Search by Roll\n6. Update Grade\n7. Display All\n8. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Roll: "); int r1 = sc.nextInt();
                    System.out.print("Name: "); String n1 = sc.next();
                    System.out.print("Age: "); int a1 = sc.nextInt();
                    System.out.print("Grade: "); String g1 = sc.next();
                    list.addAtBeginning(r1, n1, a1, g1);
                    break;
                case 2:
                    System.out.print("Roll: "); int r2 = sc.nextInt();
                    System.out.print("Name: "); String n2 = sc.next();
                    System.out.print("Age: "); int a2 = sc.nextInt();
                    System.out.print("Grade: "); String g2 = sc.next();
                    list.addAtEnd(r2, n2, a2, g2);
                    break;
                case 3:
                    System.out.print("Position: "); int pos = sc.nextInt();
                    System.out.print("Roll: "); int r3 = sc.nextInt();
                    System.out.print("Name: "); String n3 = sc.next();
                    System.out.print("Age: "); int a3 = sc.nextInt();
                    System.out.print("Grade: "); String g3 = sc.next();
                    list.addAtPosition(pos, r3, n3, a3, g3);
                    break;
                case 4:
                    System.out.print("Enter roll to delete: "); int dr = sc.nextInt();
                    list.deleteByRoll(dr);
                    break;
                case 5:
                    System.out.print("Enter roll to search: "); int sr = sc.nextInt();
                    Student found = list.searchByRoll(sr);
                    if (found != null)
                        System.out.println("Found -> Roll: " + found.roll + ", Name: " + found.name + ", Age: " + found.age + ", Grade: " + found.grade);
                    else
                        System.out.println("Record not found.");
                    break;
                case 6:
                    System.out.print("Enter roll to update: "); int ur = sc.nextInt();
                    System.out.print("Enter new grade: "); String ng = sc.next();
                    list.updateGrade(ur, ng);
                    break;
                case 7:
                    list.displayAll();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
