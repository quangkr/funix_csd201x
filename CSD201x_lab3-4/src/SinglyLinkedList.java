import java.util.function.Predicate;
import java.util.Arrays;
import java.util.Comparator;

public class SinglyLinkedList<E> {
    /**
     * Helper class implementing a Node in the list
     */
    @SuppressWarnings("hiding")
    class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E inData) {
            this.data = inData;
        }
    }

    // tail is the pointer to the LAST element
    private Node<E> head;
    private int size = 0;

    private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Get the value at given position
     * 
     * @param index Position of element to be returned
     * @return Value at given position
     */
    public E get(int index) {
        checkElementIndex(index);

        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.data;
    }

    /**
     * If this list is empty
     * 
     * @return True if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Return length of this list
     * 
     * @return Length of this list
     */
    public int size() {
        return size;
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
        prevNode.next = nodeToAdd;
        size++;
    }

    /**
     * Add an element to the start of the list
     * 
     * @param newNode
     */
    private void addFirst(Node<E> newNode) {
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Add an element to the start of the list
     * 
     * @param data Data to be added
     */
    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        addFirst(newNode);
    }
    
    public void addLast(Node<E> newNode) {
        if (isEmpty()) {
            addFirst(newNode);
            return;
        }

        Node<E> iter = head;
        while (iter.next != null) {
            iter = iter.next;
        }

        addAfter(iter, newNode);
    }
    
    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);
        addLast(newNode);
    }

    /**
     * Remove an element given its node reference
     * 
     * @param prevNode
     */
    private void removeNext(Node<E> prevNode) {
        if (isEmpty() || prevNode == null || prevNode.next == null) {
            return;
        }

        if (size == 1) {
            head = null;
        } else {
            prevNode.next = prevNode.next.next;
        }
        size--;
    }

    /**
     * Remove an element from the start of the list
     */
    public void removeFirst() {
        if (size == 1) {
            head = null;
            size--;
        } else if (size > 1) {
            head = head.next;
            size--;
        }
    }
    
    public int search(E data) {
        Node<E> iter = head;
        for (int i=0; i<size; i++) {
            if (iter.data.equals(data)) {
                return i;
            }
            iter = iter.next;
        }
        
        return -1;
    }

    private Node<E> sortedMerge(Node<E> head1, Node<E> head2, Comparator<? super E> c) {
        Node<E> newHead = new Node<>(null);
        Node<E> appendPoint = newHead;

        while (head1.next != head1) {
            appendPoint.next = head1.next;
            head1.next = head1.next.next;
            appendPoint.next.next = newHead;
            appendPoint = appendPoint.next;
        }

        while (head2.next != head2) {
            appendPoint.next = head2.next;
            head2.next = head2.next.next;
            appendPoint.next.next = newHead;
            appendPoint = appendPoint.next;
        }

        return newHead;
    }

    private Node<E> mergeSort(Node<E> head1, int length, Comparator<? super E> c) {
        if (length > 1) {
            int newLength = length / 2;
            Node<E> iter = head1.next;
            Node<E> head2 = new Node<>(null);
            for (int i = 0; i < length; i++) {
                if (i == newLength - 1) {
                    head2.next = iter.next;
                    iter.next = head1;
                    iter = head2.next;
                } else {
                    iter = iter.next;
                }
            }
            iter.next = head2;

            Node<E> returned1 = mergeSort(head1, newLength, c);
            Node<E> returned2 = mergeSort(head2, length - newLength, c);

            return sortedMerge(returned1, returned2, c);
        }

        return head1;
    }

//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    private void mergeSort(Comparator<? super E> comparator) {
//        Object[] listClone = new Object[size];
//        Node<E> temp = head;
//        for (int i = 0; i < size; i++) {
//            listClone[i] = temp.data;
//            temp = temp.next;
//        }
//
//        Arrays.sort(listClone, (Comparator) comparator);
//
//        temp = head;
//        for (int i = 0; i < size; i++) {
//            temp.data = (E) listClone[i];
//            temp = temp.next;
//        }
//    }

    private void insertSorted(Node<E> node, Comparator<? super E> c) {
        if (isEmpty() || c.compare(node.data, head.data) < 0) {
            addFirst(node);
            return;
        }
        if (size == 1) {
            if (c.compare(node.data, head.data) < 0) {
                addFirst(node);
            } else {
                addAfter(head, node);
            }
            return;
        }

        Node<E> iter = head;
        while (iter.next != null && c.compare(node.data, iter.next.data) >= 0) {
            iter = iter.next;
        }
        addAfter(iter, node);
    }

    private void insertionSort(Comparator<? super E> c) {
        if (size < 2) {
            return;
        }

        Node<E> tempHead = head;

        head = null;
        size = 0;

        Node<E> swapTemp;
        while (tempHead != null) {
            swapTemp = tempHead;
            tempHead = tempHead.next;
            insertSorted(swapTemp, c);
        }
    }

    public void sort(Comparator<? super E> c) {
        insertionSort(c);
    }

    /**
     * Print all nodes of the list
     */
    @Override
    public String toString() {
        return toString(e -> true);
    }

    /**
     * Print all nodes of the list with constraint
     */
    public String toString(Predicate<E> predicate) {
        if (isEmpty()) {
            return "There is no data in the list";
        } else {
            String result = new String();
            Node<E> temp = head;

            for (int i = 0; i < size; i++) {
                if (predicate.test(temp.data)) {
                    result += result.isEmpty() ? temp.data.toString()
                            : String.format(", %s", temp.data.toString());
                }
                temp = temp.next;
            }

            result = String.format("{%s}", result);
            return result;
        }
    }
}
