
public class Person implements Comparable<Person> {
    private String name;
    private int age;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @param name
     * @param age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        // TODO Auto-generated method stub
        return this.name.compareTo(o.getName());
//        return Integer.compare(this.age, o.getAge());
    }

    @Override
    public String toString() {
        return String.format("Name: %s, age: %d", name, age);
    }
}
