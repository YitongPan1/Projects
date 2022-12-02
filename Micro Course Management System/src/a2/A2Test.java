package a2;

/** Test harness for Assignment 2
 */
public class A2Test {
    public static void main(String[] args) {
        testStudent();
        testEmptyList();
        testContains();
        testAppend();
        testGet();
        testRemove();
        testCourseName();
        testCourseTime();
        testCourseLocation();
        testCourseProf();
        testCourseStudentsEnrollDrop();
        // The methods provided do not necessarily test everything in each
        // case.  You will need to add more to the existing testing procedures
        // as well as add new testing procedures.  You can also add tests to
        // test the Course and Student classes.
        //
        // Try to keep tests small and test features as independently as
        // possible.
    }
    public static void testStudent(){
        Student s = new Student("Bill", "Nye", 4);
        System.out.println("\nfirstname = " + s.firstName());
        System.out.println("expected = Bill");
        System.out.println("lastname = " + s.lastName());
        System.out.println("expected = Nye");
        System.out.println("fullname = " + s.fullName());
        System.out.println("expected = Bill Nye");
        System.out.println("year = " + s.year());
        System.out.println("expected = 4");
        System.out.println("course = " + s.course());
        System.out.println("expected = null");
    }


    public static void testEmptyList() {
        StudentList list = new StudentList();
        Student s = new Student("Bill", "Nye", 4);
        System.out.println("\nlist.size() = " + list.size());
        System.out.println("expected = 0");
        System.out.println("list.contains(s) = " + list.contains(s));
        System.out.println("expected = false");
    }

    public static void testContains() {
        StudentList list = new StudentList();
        Student s1 = new Student("Bill", "Nye", 4);
        Student s2 = new Student("James", "Potter", 2);
        Student s3 = new Student("Christina", "Lauren", 1);
        list.append(s1);
        list.append(s2);
        System.out.println("\nlist.contains(s3) = " + list.contains(s3));
        System.out.println("expected = false");

        System.out.println("list.contains(s2) = " + list.contains(s2));
        System.out.println("expected = true");
    }

    public static void testAppend() {
        StudentList list = new StudentList();
        Student s1 = new Student("Bill", "Nye", 4);
        list.append(s1);
        System.out.println("\nlist.size() = " + list.size());
        System.out.println("expected = 1");

        Student s2 = new Student("James", "Potter", 2);
        list.append(s2);
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 2");

        Student s3 = new Student("Helen", "Newman", 2);
        Student s4 = new Student("Christina", "Lauren", 3);
        Student s5 = new Student("Hello", "World", 4);
        Student s6 = new Student("Omisha", "Sharma", 1);
        Student s7 = new Student("Maya", "Leong", 1);
        list.append(s3);
        list.append(s4);
        list.append(s5);
        list.append(s6);
        list.append(s7);
        System.out.println("list.Append = " + list.toString());
        System.out.println("expected = [Bill Nye, James Potter, Helen Newman, Christina Lauren, Hello World, Omisha Sharma, Maya Leong]");
    }

    public static void testGet() {
        StudentList list = new StudentList();
        Student s1 = new Student("Bill", "Nye", 4);
        Student s2 = new Student("James", "Potter", 2);
        Student s3 = new Student("Helen", "Newman", 2);
        Student s4 = new Student("Christina", "Lauren", 3);
        Student s5 = new Student("Hello", "World", 4);

        list.append(s1);
        list.append(s2);
        list.append(s3);
        list.append(s4);
        list.append(s5);

        System.out.println("\nlist.get(0) = " + list.get(0));
        System.out.println("expected = Bill Nye");

        System.out.println("list.get(3) = " + list.get(3));
        System.out.println("expected = Christina Lauren");

        list.remove(s1);
        System.out.println("list.get(3) = " + list.get(3));
        System.out.println("expected = Hello World");
    }

    public static void testRemove() {
        StudentList list = new StudentList();
        Student s1 = new Student("Bill", "Nye", 4);

        list.remove(s1);
        System.out.println("\nlist.size() = " + list.size());
        System.out.println("expected = 0");

        Student s2 = new Student("James", "Potter", 2);
        Student s3 = new Student("Helen", "Newman", 2);
        list.append(s2);
        list.append(s3);
        System.out.println("list.Remove = " + list.toString());
        System.out.println("expected = [James Potter, Helen Newman]");
        list.remove(s2);
        System.out.println("list.size() = " + list.size());
        System.out.println("expected = 1");
        System.out.println("list.Remove = " + list.toString());
        System.out.println("expected = [Helen Newman]");
    }

    public static void testCourseName() {
        Course c = new Course(12, 20, "Statler Hall", "Myers", "CS 2110");
        System.out.println("\nc.getName() = " + c.getName());
        System.out.println("expected = CS 2110");
    }

    public static void testCourseTime() {
        Course c1 = new Course(12, 20, "Statler Hall", "Myers", "CS 2110");
        System.out.println("\nc1.getLectureTime() = " + c1.getLectureTime());
        System.out.println("expected = 12:20 PM");

        Course c2 = new Course(0, 58, "Statler Hall", "Myers", "CS 2110");
        System.out.println("c2.getLectureTime() = " + c2.getLectureTime());
        System.out.println("expected = 12:58 AM");

        Course c3 = new Course(23, 10, "Statler Hall", "Myers", "CS 2110");
        System.out.println("c3.getLectureTime() = " + c3.getLectureTime());
        System.out.println("expected = 11:10 PM");

        Course c4 = new Course(17, 44, "Statler Hall", "Myers", "CS 2110");
        System.out.println("c4.getLectureTime() = " + c4.getLectureTime());
        System.out.println("expected = 5:44 PM");

        Course c5 = new Course(9, 20, "Statler Hall", "Myers", "CS 2110");
        System.out.println("c5.getLectureTime() = " + c5.getLectureTime());
        System.out.println("expected = 9:20 AM");
    }

    public static void testCourseLocation() {
        Course c = new Course(12, 20, "Statler Hall", "Myers", "CS 2110");
        System.out.println("\nc.getLocation() = " + c.getLocation());
        System.out.println("expected = Statler Hall");
    }

    public static void testCourseProf() {
        Course c = new Course(12, 20, "Statler Hall", "Myers", "CS 2110");
        System.out.println("\nc.getProfessor() = " + c.getProfessor());
        System.out.println("expected = Professor Myers");
    }

    public static void testCourseStudentsEnrollDrop() {
        // getStudents method
        Course c = new Course(12, 20, "Statler Hall", "Myers", "CS 2110");
        Student s1 = new Student("Bill", "Nye", 4);
        c.enrollStudent(s1);
        System.out.print("\ns.course() = ");
        s1.course().print();
        System.out.println("\nexpected = CS 2110  12:20 PM    Myers  Statler Hall");
        System.out.println("c.getStudents() = " + c.getStudents());
        System.out.println("expected = [Bill Nye]");

        // enrollStudent method
        Student s2 = new Student("James", "Potter", 2);
        Student s3 = new Student("Helen", "Newman", 2);
        c.enrollStudent(s2);
        c.enrollStudent(s3);
        System.out.println("c.getStudents() = " + c.getStudents());
        System.out.println("expected = [Bill Nye, James Potter, Helen Newman]");
        System.out.print("s.course() = ");
        s3.course().print();
        System.out.println("\nexpected = CS 2110  12:20 PM    Myers  Statler Hall");

        // dropStudent method
        System.out.println("\n");
        Student s4 = new Student("James", "Potter", 2);
        c.enrollStudent(s4);
        System.out.println("c.getStudents() = " + c.getStudents());
        System.out.println("expected = [Bill Nye, James Potter, Helen Newman, James Potter]");
        c.dropStudent(s2);
        System.out.println("c.getStudents() = " + c.getStudents());
        System.out.println("expected = [Bill Nye, Helen Newman, James Potter]");
        System.out.print("s1.course() = ");
        s1.course().print();
        System.out.println("\nexpected = CS 2110  12:20 PM    Myers  Statler Hall");
        System.out.println("s2.course() = " + s2.course());
        System.out.println("expected = null");
    }
}