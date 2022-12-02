package a3;

import static a3.Main.csvToList;
import static a3.Main.join;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SLinkedListTest1 {
    @Test
    void emptyList() {
        LList<Integer> lst0 = new SLinkedList<>();
        assertEquals(lst0.size(), 0);
        assertEquals(lst0.head(), null);
        assertEquals(lst0.tail(), null);
        assertEquals(lst0.contains(1), false);
    }
    @Test
    void append12() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(42);
        assertEquals(l1.size(), 1);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 42);
        l1.append(21);
        assertEquals(l1.size(), 2);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 21);
        l1.append(64);
        assertEquals(l1.size(), 3);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 64);
    }
    @Test
    void prepend12() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.prepend(42);
        assertEquals(l1.size(), 1);
        assertEquals(l1.head(), 42);
        assertEquals(l1.tail(), 42);
        l1.prepend(21);
        assertEquals(l1.size(), 2);
        assertEquals(l1.head(), 21);
        assertEquals(l1.tail(), 42);
    }

    @Test
    void test_get() {
        LList<Integer> lst = new SLinkedList<>();
        for (int i = 0; i < 5; i++) lst.append(i);
        assertEquals(lst.size(), 5);
        for (int i = 0; i < 5; i++) {
            assertEquals(i, lst.get(i));
            lst.append(i);
        }
    }

    @Test
    void test_insertBefore() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.append(1);
        l1.append(2);
        l1.append(3);
        l1.append(4);
        l1.append(5);
        l1.append(6);
        l1.insertBefore(9,3);
        assertEquals(9,l1.get(2));
        l1.insertBefore(0,1);
        assertEquals("[0, 1, 2, 9, 3, 4, 5, 6]",l1.toString());
        assertEquals(0, (int) l1.head());
        l1.insertBefore(8,6);
        assertEquals("[0, 1, 2, 9, 3, 4, 5, 8, 6]",l1.toString());
    }

    @Test
    void test_toString() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        assertEquals("[]",l1.toString());
        l1.append(1);
        assertEquals("[1]",l1.toString());
        l1.append(2);
        l1.append(3);
        l1.append(4);
        l1.append(5);
        l1.append(6);
        assertEquals("[1, 2, 3, 4, 5, 6]",l1.toString());
    }

    @Test
    void test_contains() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.append(1);
        l1.append(2);
        l1.append(3);
        l1.append(4);
        l1.append(5);
        l1.append(6);
        assertTrue(l1.contains(3));
        assertTrue(l1.contains(1));
        assertTrue(l1.contains(6));
        assertFalse(l1.contains(9));
    }

    @Test
    void test_remove() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.append(1);
        l1.append(2);
        l1.append(3);
        l1.append(4);
        l1.append(5);
        l1.append(6);
        assertTrue(l1.contains(3));
        assertTrue(l1.remove(3));
        assertFalse(l1.contains(3));
        assertEquals("[1, 2, 4, 5, 6]",l1.toString());
        assertFalse(l1.remove(9));
        l1.remove(1);
        assertEquals("[2, 4, 5, 6]",l1.toString());
        assertEquals(2, (int) l1.head());
        assertTrue(l1.remove(6));
        assertEquals("[2, 4, 5]",l1.toString());
        assertEquals(5, (int) l1.tail());

    }

    @Test
    void test_csvName(){
        LList<LList<String>> ex = csvToList("example1copy.csv");
        assertEquals(ex.head().head(),"netid");
        assertEquals(ex.head().tail(),"last");
        assertEquals(ex.tail().tail(),"I");
        assertEquals(ex.tail().head(),"ghi780");
        LList<LList<String>> t1 = csvToList("t1.csv");
        assertEquals(t1.head().head(),"shape");
        assertEquals(t1.head().tail(),"color");
        assertEquals(t1.tail().tail(),"purple");
        assertEquals(t1.tail().head(),"rectangle");
        LList<LList<String>> head = csvToList("head.csv");
        assertEquals(head.head().head(),"head");
        assertEquals(head.head().tail(),"head");
        assertEquals(head.tail().tail(),"head");
        assertEquals(head.tail().head(),"head");
        //should throw IOException
        LList<LList<String>> ioex = csvToList("dne.csv");
    }

    @Test
    void test_join(){
        LList<LList<String>> ex12 = join(csvToList("example1copy.csv"),csvToList("example2copy.csv"));
        LList<LList<String>> t12 = join(csvToList("t1.csv"),csvToList("t2.csv"));
        assertEquals(ex12.head().head(),"netid");
        assertEquals(ex12.head().tail(),"grade");
        assertEquals(ex12.tail().tail(),"I");
        assertEquals(ex12.get(2).get(3),"junior");
        assertEquals(t12.head().head(),"shape");
        assertEquals(t12.head().tail(),"edges");
        assertEquals(t12.tail().tail(),"4");
        assertEquals(t12.get(2).get(1),"white");
    }

    @Test
    void test_Main(){
        String[] args1 = {"t1.csv","t2.csv"};
        String[] args2 = {"example1copy.csv","example2copy.csv"};
        Main.main(args1);
        Main.main(args2);
        String[] args3 = {"a","b","c"};
        //should call usage() method
        Main.main(args3);
    }
}