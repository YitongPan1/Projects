package a3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SLinkedListTest {
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
    void insert12() {
        LList<Integer> l1 = new SLinkedList<>();
        l1.append(3);
        l1.append(8);
        l1.append(2);
        assertEquals(l1.size(), 3);
        assertEquals(l1.head(), 3);
        assertEquals(l1.tail(), 2);
        l1.insertBefore(1,3);
        assertEquals(l1.size(), 4);
        assertEquals(l1.head(), 1);
        assertEquals(l1.tail(), 2);
        assertEquals(l1.contains(1), true);
        l1.insertBefore(6,8);
        assertEquals(l1.size(), 5);
        assertEquals(l1.head(), 1);
        assertEquals(l1.tail(), 2);
        assertEquals(l1.contains(6), true);
        assertEquals(l1.contains(9), false);
        assertEquals(l1.get(3), 8);
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
    void remove12() {
        LList<Integer> l1 = new SLinkedList<Integer>();
        l1.prepend(3);
        l1.append(8);
        l1.insertBefore(1,3);
        l1.prepend(5);
        l1.append(7);
        l1.insertBefore(2,5);
        assertEquals(l1.size(), 6);
        assertEquals(l1.head(), 2);
        assertEquals(l1.tail(), 7);
        assertEquals(l1.remove(2),true);
        assertEquals(l1.size(), 5);
        assertEquals(l1.head(), 5);
        assertEquals(l1.tail(), 7);
        assertEquals(l1.remove(3),true);
        assertEquals(l1.size(), 4);
        assertEquals(l1.head(), 5);
        assertEquals(l1.tail(), 7);
        assertEquals(l1.contains(3), false);
        assertEquals(l1.remove(7),true);
        assertEquals(l1.size(), 3);
        assertEquals(l1.head(), 5);
        assertEquals(l1.tail(), 8);
        assertEquals(l1.contains(7), false);
        assertEquals(l1.contains(1), true);
    }
}