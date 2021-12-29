
public class Lab4 {

    public static void main(String[] args) {
        SinglyLinkedList<Person> list = new SinglyLinkedList<>();
        
        list.addLast(new Person("HOA", 25));
        list.addLast(new Person("HA", 17));
        list.addLast(new Person("LAN", 26));
        list.addLast(new Person("NOI", 19));
        list.addLast(new Person("MUA", 23));
        list.addLast(new Person("NAY", 21));

        System.out.format("Traverse: %s\n", list.toString());

        list.sort((p1, p2) -> p1.compareTo(p2));
        System.out.format("Elements sort by name: %s\n", list.toString());
    }

}
