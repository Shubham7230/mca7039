
public class CustomHashMap {
    static class Entry {
        int key;
        int value;
        Entry next;
        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 10;
    private Entry[] table;

    public CustomHashMap() {
        table = new Entry[SIZE];
    }

    private int getIndex(int key) {
        return Math.abs(key) % SIZE;
    }

    public void put(int key, int value) {
        int index = getIndex(key);
        Entry newEntry = new Entry(key, value);
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry current = table[index];
            Entry prev = null;
            while (current != null) {
                if (current.key == key) {
                    current.value = value;
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = newEntry;
        }
    }

    public Integer get(int key) {
        int index = getIndex(key);
        Entry current = table[index];
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(int key) {
        int index = getIndex(key);
        Entry current = table[index];
        Entry prev = null;
        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 30);
        System.out.println(map.get(2));
        map.remove(2);
        System.out.println(map.get(2));
    }
}
