package eric;

public class LinkedList {
    public static class Node {
        public Node next = null;
        public String value;
        
        public Node(String value) {
            this.value = value;
        }
    }
    
    private Node first, tail;
    private int size;
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public String get(int index) {
        Node current = first;
        
        for (int i = 0; i < index && i < size; i++) {
            current = current.next;
        }
        return current.value;
    }
    
    public void add(String value) {
        Node newNode = new Node(value);
        
        if (isEmpty()) {
            first = newNode;
        } else {
            Node current = first;
            
            for (int i = 0; i < size - 1; i++) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    public void add(String value, int index) {
        Node newNode = new Node(value);
        
        if (isEmpty()) {
            first = newNode;
            size++;
            return;
        }
        Node current = first;
        Node previous = first;
        
        if (index == 0) {
            first = newNode;
            first.next = current;
            size++;
        } else {
            for (int x = 0; x < size && x < index; x++) {
                previous = current;
                current = current.next;
            }
            previous.next = newNode;
            newNode.next = current;
            size++;
            }
    }
    
    public void delete(int index) {
    	// TODO: add bounds check
        // Tests and deletion for a list with only one value
        if (size == 1) {
                first.value = null;
                size = 0;
                return;
            }
        // Test & deletion if the first index is being deleted
        if (index == 0){
                first = first.next;
                size--;
                return;
        }
        // Deletion for all other indexes and sizes
        Node indexNode = first;
        Node previousIndexNode = first;
        
        for (int i = 0; i < size - 1 && i < index; i++) {
            previousIndexNode = indexNode;
            indexNode = indexNode.next;
        }
        previousIndexNode.next = indexNode.next;
        size--;
    }
    
    public void clear() {
        first.next = null;
        first.value = null;
        size = 0;
    }
    
    public LinkedList toList (int[] x) {
        LinkedList list = new LinkedList();
        
        for (int i = 0; i < x.length; i++) {
            list.add(Integer.toString(x[i]));
        }
        return list;
    }
    
    public void printList (LinkedList list) {
        for (int x = 0; x < size; x++) {
            // Check for formatting
            if (x == size - 1) {
                System.out.println(list.get(x));
                return;
            }
            System.out.print(list.get(x) + ", ");
        }    
    }
}
