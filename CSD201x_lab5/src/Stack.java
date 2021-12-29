import java.util.LinkedList;
import java.util.Deque;

public class Stack<E> {
    Deque<E> storage = new LinkedList<>();

    public void push(E data) {
        storage.offerFirst(data);
    }
    
    public E pop() {
        return storage.pollFirst();
    }
    
    public E peek() {
        return storage.peekFirst();
    }
    
    public boolean isEmpty() {
        return storage.isEmpty();
    }
}
