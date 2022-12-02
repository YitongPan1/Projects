package a3;
/**
 * Please provide the following information
 *  Name: Yitong Pan
 *  NetID: yp258
 *  Testing Partner Name: Aidan Adams
 *  Testing Partner NetID: aaa289
 *  Tell us what you thought about the assignment: It's hard.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of {@code LList<T>} as a singly linked list.
 */
public class SLinkedList<T> implements LList<T> {

    /**
     * Number of values in the linked list.
     */
    private int size;
    /**
     * First and last nodes of the linked list (null if size is 0)
     */
    private Node<T> head, tail;

    /**
     * Creates: an empty linked list.
     */
    public SLinkedList() {
        size = 0;
        head = tail = null;
    }

    boolean classInv() {
        assert size >= 0;
        if (size == 0) {
            return (head == null && tail == null);
        }
        if (size > 0) {
            return (head != null && tail.next() == null);
        }
        return false;
    }

    public int size() {
        return size;
    }

    public T head() {
        if (head == null) return null;
        else return head.data();
    }

    public T tail() {
        if (tail == null) return null;
        else return tail.data();
    }

    public void prepend(T v) {
        assert classInv();
        Node<T> n = new Node<>(v, head);
        head = n;
        if (tail == null) tail = head;
        size++;
        assert classInv();
    }


    /**
     * Return a representation of this list: its values, with "[" at the beginning, "]" at the
     * end, and adjacent ones separated by ", " . Takes time proportional to the length of this
     * list. E.g. for the list containing 4 7 8 in that order, the result it "[4, 7, 8]".
     * E.g. for the list containing two empty strings, the result is "[, ]"
     */
    @Override
    public String toString() {
        // Do not modify the following 2 lines or the return statement
        assert classInv();
        StringBuilder res = new StringBuilder("[");
        Node<T> n = head;
        if(n != null) {
            while(n.next() != null) {
                res.append(n.data()+", ");
                n = n.next();
            }
            if(n.next() == null){
                res.append(n.data());
            }
        }
        res.append("]");
        return res.toString();
    }

    public void append(T v) {
        assert classInv();
        Node<T> m = new Node<>(v, null);
        if(tail == null) {
            head = tail = m;
            size++;
        }
        else {
            tail.setNext(m);
            tail = m;
            size++;
        }
        assert classInv();
    }

    public void insertBefore(T x, T y) {
        assert classInv();
        Node<T> n = head;
        Node<T> n0 = head;
        if(n0.data().equals(y)) {
            Node<T> m = new Node<>(x, head);
            head = m;
            size++;
        }
        else {
            while(n != null) {
                n0 = n;
                n = n.next();
                if(n != null && n.data().equals(y)) {
                    Node<T> me = new Node<>(x, n);
                    n0.setNext(me);
                    size++;
                    break;
                }
            }
        }
        assert classInv();
    }

    public T get(int k) {
        Node<T> n = head;
        for (int i = 0; i <= k; i++) {
            if (i == k) {
                return n.data();
            }
            n = n.next();
        }
        return null;
    }

    public boolean contains(T value) {
        Node<T> n = head;
        while(n != null) {
            if(n.data().equals(value)) {
                return true;
            }
            n = n.next();
        }
        return false;
    }

    public boolean remove(T x) {
        assert classInv();
        Node<T> n = head;
        Node<T> n0 = head;
        int a = 0;
        while(n != null) {
            if (n.data().equals(x)) {a++;}
            n = n.next();
        }
        if (a != 0) {
            if (n0.data().equals(x)) {
                head = head.next();
                size--;
            }
            else {
                n = head;
                while(n != null) {
                    n0 = n;
                    n = n.next();
                    if (n.next() != null && n.data().equals(x)) {
                        n0.setNext(n.next());
                        size--;
                        break;
                    }
                    if (n.next() == null && n.data().equals(x)) {
                        n0.setNext(null);
                        tail = n0;
                        size--;
                        break;
                    }
                }
            }
        }
        assert classInv();
        return a != 0;
    }


    /**
     * Iterator support. This method makes it possible to write
     * a for-loop over a list, e.g.:
     * <pre>
     * {@code LList<String> lst = ... ;}
     * {@code for (String s : lst) {}
     *   ... use s here ...
     * }
     * }
     */
    @Override
    public Iterator<T> iterator() {
        assert classInv();
        return new Iterator<T>() {
            private Node<T> current = head;

            public T next() throws NoSuchElementException {
                if (current != null) {
                    T result = current.data();
                    current = current.next();
                    return result;
                } else {
                    throw new NoSuchElementException();
                }
            }

            public boolean hasNext() {
                return (current != null);
            }
        };
    }
}
