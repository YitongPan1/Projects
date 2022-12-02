package a2;

public class Course {
    /**
     * List of all students enrolled in this Course.
     */
    private StudentList students = new StudentList();
    /**
     * The hour at which lecture for this Course is held (in 24-hour time). 0 <= hour <= 23
     */
    private int hour;
    /**
     * The minutes at which lecture for this Course is held. 0 <= min <= 59 The lectures for this
     * course are at hour:min
     */
    private int min;
    /**
     * The location of lectures at this course (e.g. Statler Hall Room 185) Must be non-empty
     */
    private String location;
    /**
     * The last name of the professor of this course (e.g. Myers, Muhlberger, Gries) Must be
     * non-empty
     */
    private String prof;
    /**
     * The name of this course (e.g. Object-Oriented Programming and Data Structures) Must be
     * non-empty
     */
    private String name;

    boolean classInv() {
        return hour >= 0 && hour <= 23 && min >= 0 && min <= 59 && location.length() > 0 && prof.length() > 0 && name.length() > 0;
    }

    /**
     * Constructor: Create new Course with name n, professor last name prof, location loc,<br> and
     * lectures are held at time hour:min. The course has no students. Precondition: n, prof, and
     * loc have at least one character in them, 0 <= hr <= 23, 0 <= min <= 59.
     */
    public Course(int hr, int min, String loc, String prof, String n) {
        this.hour = hr;
        this.min = min;
        this.location = loc;
        this.prof = prof;
        this.name = n;
        assert classInv();
        // Note that an empty StudentList is not the same as null
    }

    /** Return the name of this course. */
    public String getName() {
        return name;
    }

    /**
     * Return the time at which lectures are held for this course in the format hour:min AM/PM using
     * 12-hour time. For example, "11:15 AM", "1:35 PM". Add leading zeros to the minutes if necessary.
     */
    private String time;
    public String getLectureTime() {
        if (hour > 12 && min/10 == 0){
            time = hour-12 + ":0" + min + " PM";
        }
        else if (hour > 12){
            time = hour-12 + ":" + min + " PM";
        }
        else if (hour == 12 && min/10 == 0){
            time = hour + ":0" + min + " PM";
        }
        else if (hour == 12){
            time = hour + ":" + min + " PM";
        }
        else if (hour == 0 && min/10 == 0){
            time = "12:0" + min + " AM";
        }
        else if (hour == 0){
            time = "12:" + min + " AM";
        }
        else if (min/10 == 0){
            time = hour + ":0" + min + " AM";
        }
        else{
            time = hour + ":" + min + " AM";
        }
        return time;
    }

    /**
     * Return the location of lectures in this course.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Return the name of the professor in the format "Professor LastName"
     */
    public String getProfessor() {
        return "Professor " + prof;
    }

    /**
     * Return the String representation of the list of students enrolled in this course
     */
    public String getStudents() {
        if (students != null) {
            return students.toString();
        }
        else {
            return null;
        }
    }

    /**
     * Enroll a new student s to this course. If Student s is already enrolled in a course, they
     * cannot enroll in this course. Return true if the student was successfully enrolled in the
     * course.
     */
    public boolean enrollStudent(Student s) {
        assert classInv();
        if (s.course() != null) {
            return false;
        }
        else {
            students.append(s);
            s.joinCourse(new Course(hour, min, location, prof, name));
            return true;
        }
        // Remember that the class invariant of all classes must be kept true.
        // In other words, make sure that every field is correctly modified based on its
        // Javadoc comments.
    }

    /**
     * Drop Student s from this course. If Student s is not enrolled in this course, they cannot be
     * dropped from this course. Return true if the student was successfully dropped from the
     * course.
     */
    public boolean dropStudent(Student s) {
        if (students.contains(s)) {
            students.remove(s);
            s.leaveCourse();
            return true;
        }
        else {
            return false;
        }
        // Remember that the class invariant of all classes must be kept true.
        // In other words, make sure that every field is correctly modified based on its
        // Javadoc comments.
    }

    /**
     * Print the Course information in tabular format
     */
    public void print() {
        assert classInv();
        System.out.printf("%-40s%-12s%-20s%-40s",
                name, getLectureTime(), prof, location);
    }
}
