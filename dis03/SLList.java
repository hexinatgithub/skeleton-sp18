public class SLList {
    private class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            item = item;
            next = next;
        }
    }

    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void insert(int item, int position) {
        if (first == null || position == 0) {
            addFirst(item);
            return;
        }

        IntNode currentNode = first;
        while (position > 1 && currentNode.next != null) {
            currentNode = currentNode.next;
            position -= 1;
        }
        currentNode.next = new IntNode(item, currentNode.next);
    }

    private static IntNode reverse(IntNode front) {
        if (front == null || front.next == null) {
            return front;
        }

        IntNode reversedIntList = reverse(front.next);
        front.next = null;
        reversedIntList.next = front;
        return reversedIntList;
    }

    public void reverseRec() {
        first = reverse(first);
    }

    public void reverseItr() {
        IntNode frontOfReserve = null;
        IntNode nextNodeToAdd = first;
        while (nextNodeToAdd != null) {
            IntNode reminderOfOrigin = nextNodeToAdd.next;
            nextNodeToAdd.next = frontOfReserve;
            frontOfReserve = nextNodeToAdd;
            nextNodeToAdd = reminderOfOrigin;
        }
        first = frontOfReserve;
    }
}