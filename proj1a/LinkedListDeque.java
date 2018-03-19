public class LinkedListDeque<T> {

    public class ItemNode {
        ItemNode prev;
        ItemNode next;
        T item;

        public ItemNode(T item, ItemNode prev, ItemNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * the first item node (if exist) is sentinel.next
     */
    public ItemNode sentinel;
    public int size;

    public LinkedListDeque() {
        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new ItemNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = new ItemNode(item, sentinel, null);
        size = 1;
    }

    public void addFirst(T item) {
        sentinel.next = new ItemNode(item, sentinel, sentinel.next);
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new ItemNode(item, sentinel.prev, null);
        size += 1;
    }

    public boolean isEmpty() {
        return sentinel.prev == sentinel && sentinel.next == sentinel;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ItemNode p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
            System.out.print(p.item);
            System.out.print(", ");
        }

        System.out.println(p.item);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        ItemNode first = sentinel.next;
        sentinel.next = first.next;
        return first.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        ItemNode last = sentinel.prev;
        sentinel.prev = last.prev;
        return last.item;
    }

    public T get(int index) {
        if (index + 1 > size()) {
            return null;
        }

        ItemNode p = sentinel;
        while (index >= 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    public T getRecursive(ItemNode item, int index) {
        if (index == 0) {
            return item.item;
        }

        return getRecursive(item.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index + 1 > size()) {
            return null;
        }

        return getRecursive(sentinel.next, index);
    }
}