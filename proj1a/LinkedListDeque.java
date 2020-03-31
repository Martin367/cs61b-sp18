public class LinkedListDeque<T> {
    private int n;

    private class Node {
        Node pre;
        T item;
        Node next;

        Node(Node p, T x, Node n) {
            pre  = p;
            item = x;
            next = n;
        }

        Node() {
            pre = this;
            next = this;
        }
    }

    private Node sentinel;
    private int size;

    // private Node getNode(int index){
    //     Node p;
    // }

    // private LinkedListDeque cutFirst(int n){

    //     LinkedListDeque L = new LinkedListDeque(this, n);
    //     L.removeFirst();
    //     return L;    
    // }

    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.pre = sentinel;
        sentinel.next = sentinel;

        size = 0;
    }

    public int size() {
        return size;
    }
    public void addFirst(T item) {
        Node p = new Node(sentinel, item, sentinel.next);
        sentinel.next = p;
        p.next.pre = p;

        size += 1;
    }
    public void addLast(T item) {
        Node p = new Node(sentinel.pre, item, sentinel);
        sentinel.pre = p;
        p.pre.next = p;

        size += 1;
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    public void printDeque() {
        if (size == 0) {
            System.out.println();
        }
        Node p = sentinel.next;
        int i = size;
        while (i > 1) {
            System.out.print(p.item);
            p = p.next;
            System.out.print(" ");
            i--;
        }
        if (i == 1) {
            System.out.print(p.item);
        }
        System.out.println("");
            
    }
    public T removeFirst() {
        T t;
        if (size > 0) {
            t = sentinel.next.item;                
            /*reassign*/
            sentinel.next = sentinel.next.next;
            sentinel.next.pre = sentinel;                
            size -= 1;
        } else {
            t = null;
        }
        return t;
    }
    public T removeLast() {
        T t;
        if (size > 0) {
            t = sentinel.pre.item;
            sentinel.pre = sentinel.pre.pre;
            sentinel.pre.next = sentinel;
            size -= 1;
        } else {
            t = null;
        }
        return t;
    }
    public T get(int index) {
        T t;
        if (size > 0 && index < size) {
            Node p = sentinel.next;
            int i = 0;
            while (i < index) {
                p = p.next;
                i++;
            }
            t = p.item;
        } else {
            t = null;
        }
        return t;
    }
    /*public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node();
        sentinel.pre = sentinel;
        //sentinel.next = sentinel.pre ? not sure
        sentinel.next = sentinel;

        //Node p1 = sentinel.next;
        Node p1 = other.sentinel.next;
        Node p2 = sentinel.pre;
        int i = 0;
        while( i < other.size() ){
            p2 = new Node(p2.pre, p1.item, sentinel);
            p2.pre.next = p2;
            sentinel.pre = p2;
            //p2.item = p1.item;
            p1 = p1.next;
            //p2 = p2.next;
            i++;
        }

        size = other.size();
        // LinkedListDeque L = new LinkedListDeque();
        // int i = 0;
        // Node p = other.sentinel.next;
        // while(i<other.size()){
        //     L.addFirst(p.item);
        //     p = p.next;
        //     i++;
        // }
    }*/
    /*copy the front n items of the L*/
    /* private LinkedListDeque(LinkedListDeque other, int n){
    //     sentinel = new Node();
    //     sentinel.pre = sentinel;
    //     //sentinel.next = sentinel.pre ? not sure
    //     sentinel.next = sentinel.pre;
        
    //     Node p1 = other.sentinel.next;
    //     Node p2 = sentinel.pre;
    //     int i = 0;
    //     while( i < n ){
    //         p2 = new Node(p2.pre, p1.item, sentinel); 
    //         p2.pre.next = p2;
    //         sentinel.pre = p2;
    //         //p1.item = p2.item;
    //         p1 = p1.next;
    //         //p2 = p2.next;
    //     }

    //     size = n;
    }*/

    /*helper metheds*/
    private T getRecursive(Node p, int i) {
        if (size > 0 && i < size) {
            if (i == 0) {
                return p.item;
            } else {
                p = p.next;
                i -= 1;
                return getRecursive(p, i);
            }
        } else {
            return null;
        }
    }
    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }
}
