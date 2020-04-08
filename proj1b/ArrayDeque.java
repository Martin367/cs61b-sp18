public class ArrayDeque<T> implements Deque<T> {
    private T[] array;
    private int size;
    private int nextFirst, nextLast;
    private double R;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        R = 1.0 * size / array.length;
    }

    private void calR() {
        R = 1.0 * size / array.length;
    }
    private void resize() {
        T[] a = (T[]) new Object[array.length * 2];
        //System.arraycopy(array, 0, a, 0, size);
        for (int i = 1; i < size + 1; i++) {
            a[i] = array[(nextFirst + i) % array.length];
        }
        array = a;
        nextFirst = 0;
        nextLast = size + 1;
        calR();
    }
    private void downsize() {
        T[] a = (T[]) new Object[array.length / 2];
        //System.arraycopy(array, 0, a, 0, size);
        for (int i = 1; i < size + 1; i++) {
            a[i] = array[(nextFirst + i) % array.length];
        }
        array = a;
        nextFirst = 0;
        nextLast = size + 1;
        calR();
    }
    @Override
    public void addLast(T x) {
        if (size > array.length - 1) {
            resize();
        }
        array[nextLast] = x;
        size += 1;
        calR();
        nextLast += 1;
        nextLast = (nextLast + array.length) % array.length;
    }
    @Override
    public T removeLast() {
        T item;
        if (size > 0) {
            item = array[(nextLast - 1 + array.length) % array.length];
            array[(nextLast - 1 + array.length) % array.length] = null;
            size -= 1;
            calR();
            nextLast -= 1;
            nextLast = (nextLast + array.length) % array.length;
            if (R < 0.25 && array.length > 15) {
                downsize();
            }
        } else {
            item = null;
        }
        return item;
    }
    @Override
    public void addFirst(T x) {
        if (size > array.length - 1) {
            resize();
        }
        array[nextFirst] = x;        
        size += 1;
        calR();
        nextFirst -= 1;
        nextFirst = (nextFirst + array.length) % array.length;
    }
    @Override
    public T removeFirst() {
        /*int t = array[0];
        int[] a = new int[size];
        System.arraycopy(array, 1, a, 0, size-1);//System.arraycopy(array, 1, array, 0, size-1); ok?
        array = a;
        */
        T item;
        if (size > 0) {
            item = array[(nextFirst + 1) % array.length];
            array[(nextFirst + 1) % array.length] = null;
            size -= 1;
            calR();
            nextFirst += 1;
            nextFirst = (nextFirst + array.length) % array.length;
            if (R < 0.25 && array.length > 15) {
                downsize();
            }
        } else {
            item = null;
        }
        return item;
    }
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    @Override
    public T get(int index) {
        if (size > 0 && index < size) {
            return array[(index + nextFirst + 1) % array.length];
        }
        return null;        
    }    
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        for (int i = 1; i < size + 1; i++) {
            if (i < size) {
                System.out.print(array[(nextFirst + i) % array.length] + " ");
            } else {
                System.out.print(array[(nextFirst + i) % array.length]);
            }
        }
        System.out.println();
    }
    /*public int getLast() {
        return array[(nextLast-1)%array.length];        
    }*/
    /*public static void main(String[] args) {
        ArrayDeque L = new ArrayDeque();
        L.addLast(1);
        L.addLast(2);
        L.addFirst(3);
        L.addLast(4);
        L.addLast(5);
        L.addFirst(6);
        L.addFirst(7);
        L.addLast(8);
        L.addFirst(9);
        L.removeFirst();
        L.removeLast();
        L.removeLast();
        L.removeFirst();
        L.removeFirst();
        L.removeLast();
    }*/
}
