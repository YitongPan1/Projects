package a2;
/**
 * Please provide the following information
 *  Name: Yitong Pan
 *  NetID: yp258
 *  Testing Partner Name: Omisha Sharma
 *  Testing Partner NetID: os226
 *  Tell us what you thought about the assignment: It's hard.
 */

/** A mutable list of {@code Student} objects.
 */
public class StudentList {
    // Implementation: the StudentList is implemented
    // as a resizable array data structure. It should contain
    // an array that has a large enough capacity to hold all the elements,
    // plus possibly extra elements. It should be able to hold
    // a large number of students but not use up a large amount
    // of memory if it holds a small number of students.
    // You may not use any classes from the java.util package.
    /**
     * studentlist. The studentlist is a resizable array with students from [0,size-1].
     */
    private Object[] studentlist;
    /**
     * capacity. The capacity of the studentlist, should be bigger or equal to the size.
     */
    private int cap;
    /**
     * position. The position of student s in studentlist before removal, 0 <= a < size()
     */
    private int a;

    boolean classInv () {
        int rep = 0;
        for (int i = 0; i < size(); i++) {
            if (studentlist[i] == null) {
                rep += 1;
            }
        }
        return cap >= size() && rep == 0 && (a >= 0 && a < size());
    }

    /** How long you spent on this assignment */
    public static double timeSpent = 15;

    /** Constructor: A new empty {@code StudentList}. */
    public StudentList() {
        cap = 5;
        studentlist = new Object[cap];
        }
        // The capacity of the StudentList is not the same as the size.
        // The capacity is the length of the backing array.
        // We suggest starting with a capacity of 5.
        // If the backing array runs out of space, double the size of the backing array
        // and copy all elements to the new backing array


    /** The number of elements in this StudentList. */
    public int size() {
        int x = 0;
        for (int i = 0; i < cap; i++){
            if (studentlist[i] != null) {
                x++;
            }
        }
        return x;
    }

    /** The element in the list at position i. Positions start from 0.
     * Requires: 0 <= i < size of StudentList
     */
    public Student get(int i) {
        assert i >= 0 && i < studentlist.length;
        return (Student) studentlist[i];
    }

    /** Effect: Add Student s to the end of the list. */
    public void append(Student s) {
        int x;
        assert classInv ();
        if (studentlist[cap-1] == null) {
            x = size();
            studentlist[x] = s;
        }
        else {
            Object[] stu = studentlist;
            cap *= 2;
            studentlist = new Object[cap];
            for (int i = 0; i < cap/2; i++) {
                studentlist[i] = stu[i];
            }
            x = size();
            studentlist[x] = s;
        }
        assert classInv ();
        // Make sure that BEFORE adding a Student to the array, you
        // ensure that the capacity of the array is enough to add a
        // Student to it.
        // Note: Make sure you are keeping the class invariant for ALL classes true.
    }

    /** Returns: whether this list contains Student s. */
    public boolean contains(Student s) {
        int x = 0;
        for (int i = 0; i < cap; i++) {
            if (studentlist[i] == s) {
                x++;
            }
        }
        return x != 0;
    }

    /** Effect: If Student s is in this StudentList, remove Student s from the array and return true.
     * Otherwise return false. Elements other than s remain in the same relative order.
     */
    public boolean remove(Student s) {
        assert classInv ();
        int x = 0;
        for (int i = 0; i < cap; i++) {
            if (studentlist[i] == s) {
                studentlist[i] = null;
                a = i;
                x += 1;
                break;
            }
        }
        if(x != 0 && size() >= 1) {
            rearray();
        }
        return x != 0;
    }

    public void rearray() {
        for (int j = a; j < cap-1; j++) {
            studentlist[j] = studentlist[j+1];
            }
        assert classInv ();
    }

    /** The String representation of this StudentList */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(get(i));
        }
        sb.append("]");
        return sb.toString();
    }
}
