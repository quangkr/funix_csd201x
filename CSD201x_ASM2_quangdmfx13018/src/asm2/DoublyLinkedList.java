package asm2;

import java.util.Comparator;

public class DoublyLinkedList<E extends Comparable<E>> {
    /**
     * Helper class implementing a Node in the list
     */
    @SuppressWarnings("hiding")
    class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        private Node(E inData) {
            this.data = inData;
        }
    }

    // tail is the pointer to the LAST element
    private Node<E> tail;
    private Node<E> head;
    private int size = 0;

    /**
     * Find (from head) a node containing given data
     * 
     * @param dataToSearch Value to search
     * @return The first node in the list containing the given data
     */
    private Node<E> searchFromHead(E dataToSearch) {
        if (head == null) {
            return null;
        }

        Node<E> temp = head;
        while (temp != null) {
            if (temp.data == dataToSearch) {
                return temp;
            }
            temp = temp.next;
        }

        return null;
    }

    /**
     * Find (from tail) a node containing given data
     * 
     * @param dataToSearch Value to search
     * @return The last node in the list containing the given data
     */
    private Node<E> searchFromTail(E dataToSearch) {
        if (head == null) {
            return null;
        }

        Node<E> temp = tail;
        while (temp != null) {
            if (temp.data == dataToSearch) {
                return temp;
            }
            temp = temp.prev;
        }

        return null;
    }

    /**
     * If this list is empty
     * 
     * @return True if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /**
     * Return length of this list
     * 
     * @return Length of this list
     */
    public int size() {
        return size;
    }

    private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Add a node to right before another node
     * 
     * @param nextNode  Current node, where new node will be added after
     * @param nodeToAdd New node to be added
     */
    private void addBefore(Node<E> nextNode, Node<E> nodeToAdd) {
        if (nextNode == null || nodeToAdd == null) {
            return;
        }

        nodeToAdd.prev = nextNode.prev;
        nodeToAdd.next = nextNode;

        if (nextNode == head) {
            head = nodeToAdd;
        } else {
            nextNode.prev.next = nodeToAdd;
        }
        nextNode.prev = nodeToAdd;
        size++;
    }

    /**
     * Add a node to right after another node
     * 
     * @param prevNode  Current node, where new node will be added after
     * @param nodeToAdd New node to be added
     */
    private void addAfter(Node<E> prevNode, Node<E> nodeToAdd) {
        if (prevNode == null || nodeToAdd == null) {
            return;
        }

        nodeToAdd.next = prevNode.next;
        nodeToAdd.prev = prevNode;

        if (prevNode == tail) {
            tail = nodeToAdd;
        } else {
            prevNode.next.prev = nodeToAdd;
        }
        prevNode.next = nodeToAdd;
        size++;
    }

    /**
     * Add an element to the start of the list
     * 
     * @param data Data to be added
     */
    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * Add an element to the end of the list
     * 
     * @param data Data to be added
     */
    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Remove an element from the start of the list
     */
    public void removeFirst() {
        if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else if (size > 1) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    /**
     * Remove an element from the end of the list
     */
    public void removeLast() {
        if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else if (size > 1) {
            tail = tail.prev;
            tail.next = null;
            size--;
        }
    }

    /**
     * Remove an element given its node reference
     * 
     * @param nodeToDelete
     */
    private void remove(Node<E> nodeToDelete) {
        if (isEmpty() || nodeToDelete == null) {
            return;
        }

        if (head == nodeToDelete) {
            removeFirst();
        } else if (tail == nodeToDelete) {
            removeLast();
        } else {
            nodeToDelete.prev.next = nodeToDelete.next;
            nodeToDelete.next.prev = nodeToDelete.prev;
        }
        size--;
    }

    /**
     * Remove first occurrence of given data
     * 
     * @param data Data to be removed
     */
    public void removeFirstOccurrence(E data) {
        remove(searchFromHead(data));
    }

    /**
     * Remove last occurrence of given data
     * 
     * @param data Data to be removed
     */
    public void removeLastOccurrence(E data) {
        remove(searchFromTail(data));
    }

    /**
     * Remove first occurrence of given data
     * 
     * @param data Data to be removed
     */
    public void remove(E data) {
        removeFirstOccurrence(data);
    }

    /**
     * Get the value at given position
     * 
     * @param index Position of element to be returned
     * @return Value at given position
     */
    public E get(int index) {
        checkElementIndex(index);

        if (index < (size / 2)) {
            Node<E> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }

            return temp.data;
        } else {
            Node<E> temp = tail;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }

            return temp.data;
        }
    }

    /**
     * Get the first element of this list
     * 
     * @return First element
     */
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    /**
     * Get the last element of this list
     * 
     * @return Last element
     */
    public E getLast() {
        if (isEmpty()) {
            return null;
        }
        return tail.data;
    }

    private void quickSort(Node<E> start, Node<E> end, Comparator<? super E> comparator) {
        // base cases
        if (start == null || end == null || start == end || end.next == start) {
            return;
        }

        // temporary variables
        Node<E> start1 = start;
        Node<E> end1 = end;
        Node<E> end2 = end;
        Node<E> nodeToCheck = end;
        Node<E> temp;
        // doing the PARTITION part in traditional quick sort
        // using end node as the pivot, checking all node against
        // the pivot, EXCEPT the start node
        while (nodeToCheck.prev != start) {
            if (comparator.compare(nodeToCheck.prev.data, end.data) > 0) {
                // move any node greater than pivot to RIGHT AFTER it
                if (end2 == end) {
                    end2 = nodeToCheck.prev;
                }
                temp = nodeToCheck.prev;
                remove(nodeToCheck.prev);
                addAfter(end, temp);
            } else if (comparator.compare(nodeToCheck.prev.data, end.data) == 0) {
                // this is an optimization
                // move any node EQUAL to pivot to RIGHT BEFORE it
                // making a cluster of nodes that equal to pivot
                // we can then mark the start of this cluster as
                // the end of left partition
                temp = nodeToCheck.prev;
                remove(nodeToCheck.prev);
                addBefore(end1, temp);
                end1 = temp;
                if (nodeToCheck.prev == end1) {
                    nodeToCheck = end1;
                }
            } else {
                // this node is less than pivot, so we leave it and proceed
                // to the previous node
                nodeToCheck = nodeToCheck.prev;
            }
        }

        // if the start node is also needed to be moved, mark its next
        // node as the new start of the left partition
        if (comparator.compare(start.data, end.data) > 0) {
            start1 = nodeToCheck;
            temp = start;
            remove(start);
            addAfter(end, temp);
        }

        // recursion part
        quickSort(start1, end1.prev, comparator);
        quickSort(end.next, end2, comparator);
    }

    public void sort(Comparator<? super E> comparator) {
        quickSort(head, tail, comparator);
    }

    /**
     * Print all nodes of the list
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "There is no data in the list";
        } else {
            String result = "";
            Node<E> temp = head;
            result += "{";
            result += temp.data.toString();
            temp = temp.next;

            while (temp != null) {
                result += String.format(", %s", temp.data.toString());
                temp = temp.next;
            }

            result += "}";

            return result;
        }
    }
}