
public class Lab3 {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        int x = 8;
        
        list.addFirst(7);
        list.addFirst(2);
        list.addFirst(9);
        list.addFirst(8);
        list.addFirst(6);
        list.addFirst(3);

        System.out.format("Traverse: %s\n", list.toString());
        System.out.format("Search (%d): %d\n", x, list.search(x));
        System.out.format("Elements greater than %d: %s\n", x, list.toString(n -> n>x));

        list.sort((p1, p2) -> p1.compareTo(p2));
        System.out.format("Elements sort by name: %s\n", list.toString());
    }

}
