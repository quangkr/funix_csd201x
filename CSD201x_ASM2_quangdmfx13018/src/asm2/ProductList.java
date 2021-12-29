package asm2;

public class ProductList {
    private final DoublyLinkedList<Product> list = new DoublyLinkedList<>();

    public void add(Product p) {
        list.addLast(p);
    }
    
    public Product getProductById(String productId) {
        for (int i=0; i<list.size(); i++) {
            Product p = list.get(i);
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        
        return null;
    }
    
    public void remove(String productId) {
        Product p = getProductById(productId);
        if (p != null) {
            list.remove(p);
        }
    }
    
    public void sort() {
        list.sort((p1, p2) -> p1.compareTo(p2));
    }
    
    public int size() {
        return list.size();
    }
    
    @Override
    public String toString() {
        return list.toString();
    }
}
