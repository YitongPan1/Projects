package a2;
public class Student {
    /**
     * First name of this Student. f.length() > 0
     */
    private String firstName;
    /**
     * Last name of this Student. l.length() > 0
     */
    private String lastName;
    /**
     * year. The year this Student is in school. E.g. 1 if Freshman, 2 if Sophomore, etc. y > 0
     */
    private int year;
    /**
     * course. The Course this Student is enrolled in. This Student may be enrolled in at most 1 course.
     */
    private Course course;

    boolean classInv () {
        return firstName.length() > 0 && lastName.length() > 0 && year > 0;
    }

    // Declare the following fields. These fields will hold information describing each Student.
    // Make the fields private and include comments describing each of them in the form of a class invariant
    // * firstName. First name of this Student. Must be a non-empty String.
    // * lastName. Last name of this Student. Must be a non-empty String.
    // * year. The year this Student is in school. E.g. 1 if Freshman, 2 if Sophomore, etc. Must be > 0
    // * course. The Course this Student is enrolled in. This Student may be enrolled in at most 1 course.
    //  null if this Student is not enrolled in any course.

    /** Constructor: Create a new Student with first name f, last name l, and year y.
     * This student is not enrolled in any Courses.
     * Requires: f and l have at least one character and y > 0. */
    public Student(String f, String l, int y) {
        this.firstName = f;
        this.lastName = l;
        this.year = y;
        assert classInv();
    }

    /** The first name of this Student. */
    public String firstName() {
        return firstName;
    }

    /** The last name of this Student. */
    public String lastName() {
        return lastName;
    }

    /** The first and last name of this Student in the format "First Last". */
    public String fullName() {
        return firstName + " " + lastName;
    }

    /** The year in school this Student is in. */
    public int year() {
        return year;
    }

    /** The course this student is enrolled in. */
    public Course course() {
        return course;
    }

    /** Enroll this Student in Course c.
     * Requires: The student is not enrolled in a course already.*/
    public void joinCourse(Course c) {
        course = c;
    }

    /**
     * Drop this Student from their Course. Requires: This student is enrolled in a course already.
     */
    public void leaveCourse() {
        course = null;
    }

    /** Return the full name of this Student */
    public String toString() {
        return fullName();
    }
}