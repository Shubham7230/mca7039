import java.util.Scanner;

class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    Task head, tail, current;

    void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
    }

    void addAtPosition(int pos, int id, String name, int priority, String dueDate) {
        if (pos <= 1 || head == null) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++)
            temp = temp.next;
        newTask.next = temp.next;
        temp.next = newTask;
        if (temp == tail)
            tail = newTask;
    }

    void removeById(int id) {
        if (head == null) return;
        if (head.id == id) {
            if (head == tail) head = tail = null;
            else {
                head = head.next;
                tail.next = head;
            }
            return;
        }
        Task temp = head;
        while (temp.next != head && temp.next.id != id)
            temp = temp.next;
        if (temp.next.id == id) {
            if (temp.next == tail) tail = temp;
            temp.next = temp.next.next;
        }
    }

    void viewCurrentTask() {
        if (current == null) current = head;
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Current Task -> ID: " + current.id + ", Name: " + current.name + ", Priority: " + current.priority + ", Due: " + current.dueDate);
    }

    void moveToNextTask() {
        if (current == null) current = head;
        else current = current.next;
        viewCurrentTask();
    }

    void displayAll() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        do {
            System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks found.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) System.out.println("No task found with priority " + priority);
    }
}

public class TaskSchedulerSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler ts = new TaskScheduler();
        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by ID\n5. View Current Task\n6. Move to Next Task\n7. Display All\n8. Search by Priority\n9. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("ID: "); int id1 = sc.nextInt();
                    System.out.print("Name: "); String n1 = sc.next();
                    System.out.print("Priority: "); int p1 = sc.nextInt();
                    System.out.print("Due Date: "); String d1 = sc.next();
                    ts.addAtBeginning(id1, n1, p1, d1);
                    break;
                case 2:
                    System.out.print("ID: "); int id2 = sc.nextInt();
                    System.out.print("Name: "); String n2 = sc.next();
                    System.out.print("Priority: "); int p2 = sc.nextInt();
                    System.out.print("Due Date: "); String d2 = sc.next();
                    ts.addAtEnd(id2, n2, p2, d2);
                    break;
                case 3:
                    System.out.print("Position: "); int pos = sc.nextInt();
                    System.out.print("ID: "); int id3 = sc.nextInt();
                    System.out.print("Name: "); String n3 = sc.next();
                    System.out.print("Priority: "); int p3 = sc.nextInt();
                    System.out.print("Due Date: "); String d3 = sc.next();
                    ts.addAtPosition(pos, id3, n3, p3, d3);
                    break;
                case 4:
                    System.out.print("Enter Task ID to remove: "); int rid = sc.nextInt();
                    ts.removeById(rid);
                    break;
                case 5:
                    ts.viewCurrentTask();
                    break;
                case 6:
                    ts.moveToNextTask();
                    break;
                case 7:
                    ts.displayAll();
                    break;
                case 8:
                    System.out.print("Enter Priority: "); int sp = sc.nextInt();
                    ts.searchByPriority(sp);
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
