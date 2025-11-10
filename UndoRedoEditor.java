import java.util.*;

class TextState {
    String content;
    TextState prev, next;
    TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    TextState head, current;
    int size = 0;
    final int MAX_SIZE = 10;

    void addState(String content) {
        TextState newState = new TextState(content);
        if (head == null) {
            head = newState;
            current = head;
            size = 1;
            return;
        }
        if (current.next != null) current.next.prev = null;
        current.next = newState;
        newState.prev = current;
        current = newState;
        size++;
        trimHistory();
    }

    void trimHistory() {
        int count = 0;
        TextState temp = current;
        while (temp.prev != null) {
            count++;
            temp = temp.prev;
            if (count >= MAX_SIZE) {
                temp.prev = null;
                head = temp;
                break;
            }
        }
    }

    void undo() {
        if (current != null && current.prev != null)
            current = current.prev;
        else
            System.out.println("No more undo available.");
    }

    void redo() {
        if (current != null && current.next != null)
            current = current.next;
        else
            System.out.println("No more redo available.");
    }

    void showCurrent() {
        if (current == null) System.out.println("No content.");
        else System.out.println("Current Text: " + current.content);
    }

    void displayHistory() {
        TextState temp = head;
        System.out.println("\nHistory:");
        while (temp != null) {
            System.out.println(temp.content + (temp == current ? " <-- current" : ""));
            temp = temp.next;
        }
    }
}

public class UndoRedoEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditor editor = new TextEditor();
        while (true) {
            System.out.println("\n1. Type/Add Text\n2. Undo\n3. Redo\n4. Show Current\n5. Show History\n6. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter new text: ");
                    String text = sc.nextLine();
                    editor.addState(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.showCurrent();
                    break;
                case 5:
                    editor.displayHistory();
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }
}
