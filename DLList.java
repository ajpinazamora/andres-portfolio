//****************************************************************
// Andres Joab Pina Zamora
//
// Java Templated Doubly Linked List Class
//****************************************************************

class DLList<T> {

    //****************************************************************
    // DLListNode nested class
    //****************************************************************
    private static class DLListNode<T> {
        T data;
        DLListNode<T> prev;
        DLListNode<T> next;

        // Constructor
        DLListNode(T item, DLListNode<T> p, DLListNode<T> n) {
            data = item;
            prev = p;
            next = n;
        }
    }

    //****************************************************************
    // DLList private data members
    //****************************************************************
    private DLListNode<T> header;
    private DLListNode<T> trailer;
    private DLListNode<T> current;
    private int size = 0;

    //****************************************************************
    // Default constructor
    //****************************************************************
    DLList() {
        header = new DLListNode<>(null, null, null);
        trailer = new DLListNode<>(null, header, null);
        header.next = trailer;
        trailer.prev = header;
        current = null;
    }

    //****************************************************************
    // Copy constructor (Shallow Copy)
    //****************************************************************
    DLList(DLList<T> other) {
        this();
        DLListNode<T> temp = other.header.next;
        while (temp != other.trailer) {
            insertLast(temp.data);
            temp = temp.next;
        }
        if (!isEmpty()) {
            current = header.next;
        }
    }

    //****************************************************************
    // Delete all nodes
    //****************************************************************
    void clear() {
        header.next = trailer;
        trailer.prev = header;
        current = null;
        size = 0;
    }

    //****************************************************************
    // Return the number of nodes
    //****************************************************************
    int size() {
        return size;
    }

    //****************************************************************
    // Return whether the list is empty
    //****************************************************************
    boolean isEmpty() {
        return size == 0;
    }

    //****************************************************************
    // Return whether current is at the first node
    //****************************************************************
    boolean atFirst() {
        return current == header.next;
    }

    //****************************************************************
    // Return whether current is at the last node
    //****************************************************************
    boolean atLast() {
        return current == trailer.prev;
    }

    //****************************************************************
    // Move current to the first node
    //****************************************************************
    boolean first() {
        if (isEmpty()) return false;
        current = header.next;
        return true;
    }

    //****************************************************************
    // Move current to the last node
    //****************************************************************
    boolean last() {
        if (isEmpty()) return false;
        current = trailer.prev;
        return true;
    }

    //****************************************************************
    // Move current to the next node
    //****************************************************************
    boolean next() {
        if (current == null || current.next == trailer) return false;
        current = current.next;
        return true;
    }

    //****************************************************************
    // Move current to the previous node
    //****************************************************************
    boolean previous() {
        if (current == null || current.prev == header) return false;
        current = current.prev;
        return true;
    }

    //****************************************************************
    // Move current to the node at a given index
    //****************************************************************
    boolean seek(int loc) {
        if (loc < 0 || loc >= size) return false;
        current = header.next;
        for (int i = 0; i < loc; i++) {
            current = current.next;
        }
        return true;
    }

    //****************************************************************
    // Return data at current node
    //****************************************************************
    T dataRead() {
        return (current == null) ? null : current.data;
    }

    //****************************************************************
    // Update data at current node
    //****************************************************************
    void dataWrite(T item) {
        if (current != null) {
            current.data = item;
        }
    }

    //****************************************************************
    // Insert at the beginning
    //****************************************************************
    void insertFirst(T item) {
        DLListNode<T> newNode = new DLListNode<>(item, header, header.next);
        header.next.prev = newNode;
        header.next = newNode;
        current = newNode;
        size++;
    }

    //****************************************************************
    // Insert at the end
    //****************************************************************
    void insertLast(T item) {
        DLListNode<T> newNode = new DLListNode<>(item, trailer.prev, trailer);
        trailer.prev.next = newNode;
        trailer.prev = newNode;
        current = newNode;
        size++;
    }

    //****************************************************************
    // Insert at current position
    //****************************************************************
    void insertAtCurrent(T item) {
        if (isEmpty()) {
            insertFirst(item);
            return;
        }
        DLListNode<T> newNode = new DLListNode<>(item, current.prev, current);
        current.prev.next = newNode;
        current.prev = newNode;
        current = newNode;
        size++;
    }

    //****************************************************************
    // Delete the first node
    //****************************************************************
    void deleteFirst() {
        if (isEmpty()) return;
        if (size == 1) {
            clear();
            return;
        }
        header.next = header.next.next;
        header.next.prev = header;
        current = header.next;
        size--;
    }

    //****************************************************************
    // Delete the last node
    //****************************************************************
    void deleteLast() {
        if (isEmpty()) return;
        if (size == 1) {
            clear();
            return;
        }
        trailer.prev = trailer.prev.prev;
        trailer.prev.next = trailer;
        current = trailer.prev;
        size--;
    }

    //****************************************************************
    // Delete at current position
    //****************************************************************
    void deleteAtCurrent() {
        if (current == null || isEmpty()) return;

        DLListNode<T> prevNode = current.prev;
        DLListNode<T> nextNode = current.next;

        if (current == header.next) {
            header.next = nextNode;
        }
        if (current == trailer.prev) {
            trailer.prev = prevNode;
        }

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        if (nextNode == trailer) {
            current = prevNode;
        } else {
            current = nextNode;
        }

        size--;

        if (size == 0) {
            current = null;
        }
    }
}