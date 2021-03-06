import java.util.Formatter;

/**
 * A naked recursive list of integers, similar to what we saw in lecture 3, but
 * with a large number of additional methods.
 *
 * @author P. N. Hilfinger, with some modifications by Josh Hug and melaniecebula
 *         [Do not modify this file.]
 */
public class IntList {
    /**
     * First element of list.
     */
    int first;
    /**
     * Remaining elements of list.
     */
    IntList rest;

    /**
     * A List with first FIRST0 and rest REST0.
     */
    public IntList(int first0, IntList rest0) {
        first = first0;
        rest = rest0;
    }

    /**
     * A List with null rest, and first = 0.
     */
    public IntList() {
    /* NOTE: public IntList () { }  would also work. */
        this(0, null);
    }
    /*helper method*/
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }
    public Integer get(int index) {
        if (this != null && index < this.size()) {
            if (index == 0) {
                return first;
            }
            return this.rest.get(index - 1);
        }
        return null;
    }
    public void insert(int x, int pos) {
        if (pos == 1) {
            IntList L = new IntList(x, this.rest);
            this.rest = L;
        } else {
            this.rest.insert(x, pos - 1);
        }
    }
    public void insertItera(int x, int pos) {
        IntList p = this;
        for (int i = 0; i < pos - 1; i += 1) {
            p = p.rest;
        }
        IntList L = new IntList(x, p.rest);
        p.rest = L;
    }
    /**
     * Returns a list equal to L with all elements squared. Destructive.
     */
    public static void dSquareList(IntList L) {

        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first * L.first, null);
        IntList ptr = res;
        L = L.rest;
        while (L != null) {
            ptr.rest = new IntList(L.first * L.first, null);
            L = L.rest;
            ptr = ptr.rest;
        }
        return res;
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first * L.first, squareListRecursive(L.rest));
    }

    /** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */


    /**
     * Returns a list consisting of the elements of A followed by the
     * *  elements of B.  May modify items of A. Don't use 'new'.
     */
    /**iterative Destructive.
    public static IntList dcatenate(IntList A, IntList B) {
        if (A != null) {
            IntList L = A;
            while (L.rest != null) {
                L = L.rest;
            }
            L.rest = B;
            return A;
        } else if (B != null) {
            return B;
        }
        return null;
    }*/
    /*
    *recursive Destructive.
    */
    public static IntList dcatenate(IntList A, IntList B) {
        if (A != null) {
            if (A.rest == null) {
                A.rest = B;
                return A;
            }
            dcatenate(A.rest, B);
            return A;
        } else if (B != null) {
            return B;
        }
        return null;
    }    
    /**
     * Returns a list consisting of the elements of A followed by the
     * * elements of B.  May NOT modify items of A.  Use 'new'.
     */
    /**iterative Non-destructive.
    public static IntList catenate(IntList A, IntList B) {
        if (A != null) {
            IntList res = new IntList(A.first, null);
            IntList ptr = res;
            A = A.rest;
            while (A != null) {
                ptr.rest = new IntList(A.first, null);
                A = A.rest;
                ptr = ptr.rest;
            }
            while (B != null) {
                ptr.rest = new IntList(B.first, null);
                B = B.rest;
                ptr = ptr.rest;
            }
            //ptr.rest = B;
            return res;
        } else if (B != null) {
            return B;
        }

        return null;
    }*/
    //recursive Non-destructive.
    private static IntList copy(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.first, copy(L.rest));
    }
    public static IntList catenate(IntList A, IntList B) {
        IntList res = new IntList(); 
        res = copy(A);
        if (res != null) {
            IntList ptr = res;
            while (ptr.rest != null) {
                ptr = ptr.rest;
            }
            ptr.rest = copy(B);    
        } else {
            res = copy(B);
        }
        return res;
    }

    /**
    * Returns the reverse of the given IntList.
    * This method is destructive. If given null
    * as an input, returns null.
    */
    private IntList lastList() {
        if (this.rest == null) {
            return this;
        }
        return this.rest.lastList();
    }
    private void backward(int front) {
        if (this.rest != null) {        
            int back;
            back = this.rest.first;
            this.rest.first = front;
            this.rest.backward(back);            
        }
    }
    public static IntList reverse(IntList L) {
        if (L != null) {
            IntList ptr = L;
            while (ptr.rest != null) {
                int last = ptr.lastList().first;
                ptr.backward(ptr.first);
                ptr.first = last;
                ptr = ptr.rest;
            }
        }
        return L;   
    }















    /**
     * DO NOT MODIFY ANYTHING BELOW THIS LINE! Many of the concepts below here
     * will be introduced later in the course or feature some form of advanced
     * trickery which we implemented to help make your life a little easier for
     * the lab.
     */

    @Override
    public int hashCode() {
        return first;
    }

    /**
     * Returns a new IntList containing the ints in ARGS. You are not
     * expected to read or understand this method.
     */
    public static IntList of(Integer... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    /**
     * Returns true iff X is an IntList containing the same sequence of ints
     * as THIS. Cannot handle IntLists with cycles. You are not expected to
     * read or understand this method.
     */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.rest, L = L.rest) {
            if (p.first != L.first) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    /**
     * If a cycle exists in the IntList, this method
     * returns an integer equal to the item number of the location where the
     * cycle is detected.
     * <p>
     * If there is no cycle, the number 0 is returned instead. This is a
     * utility method for lab2. You are not expected to read, understand, or
     * even use this method. The point of this method is so that if you convert
     * an IntList into a String and that IntList has a loop, your computer
     * doesn't get stuck in an infinite loop.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }

        int cnt = 0;


        while (true) {
            cnt++;
            if (hare.rest != null) {
                hare = hare.rest.rest;
            } else {
                return 0;
            }

            tortoise = tortoise.rest;

            if (tortoise == null || hare == null) {
                return 0;
            }

            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    /** Outputs the IntList as a String. You are not expected to read
     * or understand this method. */
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.rest) {
            out.format("%s%d", sep, p.first);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }
}

