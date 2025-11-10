import java.util.Scanner;

class Item {
    String name;
    int id;
    int quantity;
    double price;
    Item next;

    Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    Item head;

    void addAtBeginning(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    void addAtEnd(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newItem;
    }

    void addAtPosition(int pos, String name, int id, int quantity, double price) {
        if (pos <= 1 || head == null) {
            addAtBeginning(name, id, quantity, price);
            return;
        }
        Item newItem = new Item(name, id, quantity, price);
        Item temp = head;
        for (int i = 1; i < pos - 1 && temp.next != null; i++)
            temp = temp.next;
        newItem.next = temp.next;
        temp.next = newItem;
    }

    void removeById(int id) {
        if (head == null) return;
        if (head.id == id) {
            head = head.next;
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.id != id)
            temp = temp.next;
        if (temp.next != null)
            temp.next = temp.next.next;
    }

    void updateQuantity(int id, int newQty) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.quantity = newQty;
                return;
            }
            temp = temp.next;
        }
    }

    void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                System.out.println("Found -> ID: " + temp.id + ", Name: " + temp.name + ", Qty: " + temp.quantity + ", Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    void searchByName(String name) {
        Item temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("Found -> ID: " + temp.id + ", Name: " + temp.name + ", Qty: " + temp.quantity + ", Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    void totalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: " + total);
    }

    void sortByName(boolean ascending) {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Item temp = head;
            while (temp.next != null) {
                if ((ascending && temp.name.compareToIgnoreCase(temp.next.name) > 0) ||
                    (!ascending && temp.name.compareToIgnoreCase(temp.next.name) < 0)) {
                    swap(temp, temp.next);
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }

    void sortByPrice(boolean ascending) {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Item temp = head;
            while (temp.next != null) {
                if ((ascending && temp.price > temp.next.price) ||
                    (!ascending && temp.price < temp.next.price)) {
                    swap(temp, temp.next);
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }

    void swap(Item a, Item b) {
        String n = a.name;
        int i = a.id;
        int q = a.quantity;
        double p = a.price;
        a.name = b.name;
        a.id = b.id;
        a.quantity = b.quantity;
        a.price = b.price;
        b.name = n;
        b.id = i;
        b.quantity = q;
        b.price = p;
    }

    void displayAll() {
        if (head == null) {
            System.out.println("No items in inventory.");
            return;
        }
        Item temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.id + ", Name: " + temp.name + ", Qty: " + temp.quantity + ", Price: " + temp.price);
            temp = temp.next;
        }
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inv = new Inventory();
        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by ID\n5. Update Quantity\n6. Search by ID\n7. Search by Name\n8. Total Value\n9. Sort by Name\n10. Sort by Price\n11. Display All\n12. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Name: "); String n1 = sc.next();
                    System.out.print("ID: "); int i1 = sc.nextInt();
                    System.out.print("Qty: "); int q1 = sc.nextInt();
                    System.out.print("Price: "); double p1 = sc.nextDouble();
                    inv.addAtBeginning(n1, i1, q1, p1);
                    break;
                case 2:
                    System.out.print("Name: "); String n2 = sc.next();
                    System.out.print("ID: "); int i2 = sc.nextInt();
                    System.out.print("Qty: "); int q2 = sc.nextInt();
                    System.out.print("Price: "); double p2 = sc.nextDouble();
                    inv.addAtEnd(n2, i2, q2, p2);
                    break;
                case 3:
                    System.out.print("Position: "); int pos = sc.nextInt();
                    System.out.print("Name: "); String n3 = sc.next();
                    System.out.print("ID: "); int i3 = sc.nextInt();
                    System.out.print("Qty: "); int q3 = sc.nextInt();
                    System.out.print("Price: "); double p3 = sc.nextDouble();
                    inv.addAtPosition(pos, n3, i3, q3, p3);
                    break;
                case 4:
                    System.out.print("Enter ID: "); int rid = sc.nextInt();
                    inv.removeById(rid);
                    break;
                case 5:
                    System.out.print("Enter ID: "); int uid = sc.nextInt();
                    System.out.print("New Quantity: "); int uq = sc.nextInt();
                    inv.updateQuantity(uid, uq);
                    break;
                case 6:
                    System.out.print("Enter ID: "); int sid = sc.nextInt();
                    inv.searchById(sid);
                    break;
                case 7:
                    System.out.print("Enter Name: "); String sname = sc.next();
                    inv.searchByName(sname);
                    break;
                case 8:
                    inv.totalValue();
                    break;
                case 9:
                    System.out.print("Ascending(1) or Descending(0): "); int an = sc.nextInt();
                    inv.sortByName(an == 1);
                    break;
                case 10:
                    System.out.print("Ascending(1) or Descending(0): "); int ap = sc.nextInt();
                    inv.sortByPrice(ap == 1);
                    break;
                case 11:
                    inv.displayAll();
                    break;
                case 12:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

