import java.util.Set;

public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    private static int rfactor = 2;
    private static double usage = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    public ArrayDeque(T item) {
        this();
        this.addFirst(item);
    }

    private int arrayIndex(int i) {
        return Math.floorMod(i, items.length);
    }

    private void resize(int capacity) {
        if (capacity > items.length) {
            T[] new_items = (T[]) new Object[capacity];
            System.arraycopy(items, 0, new_items, 0, nextFirst + 1);
            int newFirst = new_items.length - items.length + nextFirst;
            System.arraycopy(items, nextFirst + 1, new_items, newFirst + 1, items.length - nextFirst - 1);
            items = new_items;
            nextFirst = newFirst;
        } else {
            T[] new_items = (T[]) new Object[capacity];
            for (int i = 0; i < items.length / capacity; i++) {
                int destPost = i * capacity;
                for (int j = 0; j < capacity; j++) {
                    T destItem = items[destPost + j];
                    if (destItem != null) {
                        new_items[j] = destItem;
                    }
                }
            }
            items = new_items;
            nextFirst = arrayIndex(nextFirst);
            nextLast = arrayIndex(nextLast);
        }
    }

    public void addFirst(T item) {
        if (items[nextFirst] != null) {
            resize(size * rfactor);
        }

        items[nextFirst] = item;
        nextFirst = arrayIndex(nextFirst - 1);
        size += 1;
    }

    public void addLast(T item) {
        if (items[nextFirst] != null) {
            resize(size * rfactor);
        }

        items[nextLast] = item;
        nextLast = arrayIndex(nextLast + 1);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = arrayIndex(nextFirst + 1);

        while (i != nextLast) {
            System.out.print(items[i]);
            System.out.print(", ");
            i = arrayIndex(i + 1);
        }

        System.out.println();
    }

    public T removeFirst() {
        if (((double)size / items.length) < usage) {
            resize(items.length / 2);
        }

        int first = arrayIndex(nextFirst + 1);
        T firstItem = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        return firstItem;
    }

    public T removeLast() {
        double u = (double) size / items.length;
        if (((double)size / items.length) <= usage) {
            resize(items.length / 2);
        }

        int lastTest = Math.floorMod(-1, 16);
        int last = arrayIndex(nextLast - 1);
        T lastItem = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;
        return lastItem;
    }

    public T get(int index) {
        int i = arrayIndex(nextFirst + index);
        return items[i];
    }
}
