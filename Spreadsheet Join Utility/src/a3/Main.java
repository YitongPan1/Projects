package a3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /**
     * Replace "-1" by the time you spent on A3 in hours. Example: for 3 hours 15 minutes, use 3.25
     * Example: for 4 hours 30 minutes, use 4.50 Example: for 5 hours, use 5 or 5.0
     */
    public static double timeSpent = 16;

    /**
     * Write a function that takes in a csv file name as a string and returns a Linked List
     * representation of the CSV table.
     */
    public static LList<LList<String>> csvToList(String file) {
        LList<String> List = new SLinkedList<>();
        LList<LList<String>> LList = new SLinkedList<>();
        try (BufferedReader a = new BufferedReader(new FileReader(file))) {
            String line = a.readLine();
            String[] first = line.split(",");
            int n = first.length;
            while (line != null) {
                String[] field = line.split(",");
                if(n == field.length) {
                    for (int i = 0; i < n; i++) {
                        List.append(field[i]);
                    }
                }
                else {
                    for (int i = 0; i < field.length; i++) {
                        List.append(field[i]);
                    }
                    for (int i = field.length; i < n; i++) {
                        List.append("");
                    }
                }
                LList.append(List);
                List = new SLinkedList<>();
                line = a.readLine();
            }
        } catch (IOException e) {
            usage();
            throw new RuntimeException(e);
        }
        return LList;
    }

    /**
     * Write a function that takes in two lists representing tables and
     * computes their left join.
     */
    public static LList<LList<String>> join(LList<LList<String>> table1,
                                            LList<LList<String>> table2) {
        table2 = edit2(table1,table2); // delete duplicated content
        LList<String> List1;
        LList<String> List2;
        LList<LList<String>> LList = new SLinkedList<>();
        int m = 0;
        /** find the same place in the first row */
        for (int i = 0; i < table1.size(); i++) {
            List1 = table1.get(i); // get the i row of table1
            for (int j = 0; j < table2.size(); j++) {
                List2 = table2.get(j); // get the j row of table 2
                for (String name1 : List1) { // the content in i row
                    for (String name2 : List2) { // the content in j row
                        m++;
                        if (name2.equals(name1)) {
                            String next = nthvalue(m+1,List2);
                            List2.remove(name2);
                            add(List1, List2);
                            if(next != null) {
                                List2.insertBefore(name2,next);
                            }
                            else{
                                List2.append(name2);
                            }
                        }
                        break;
                    }
                    m = 0;
                }
            }
            LList.append(List1);
            List1 = null;
            List2 = null;
        }
        return LList;
    }

    /**
     * Effect: Get the value on the (n)th place of the List.
     * Return: the nth value.
     */
    public static String nthvalue(int n, LList<String> List) {
        int i = 0;
        for(String nstr : List){
            i++;
            if(i == n){
                return nstr;
            }
        }
        return null;
    }

    /**
     * Effect: Edit table 2 to delete the same column as table 1 (expect for the first equal column).
     * Return: New table 2.
     */
    public static LList<LList<String>> edit2(LList<LList<String>> table1,
            LList<LList<String>> table2) {
        LList<String> List1 = table1.get(0);
        LList<String> List2 = table2.get(0);
        LList<Integer> List3 = new SLinkedList<>();
        int n = 0;
        int m = 0;
        for(String name2 : List2) {
            n++;
            for(String name1 : List1){
                if(name1.equals(name2)) {List3.prepend(n);}
            }
        }
        int first = 0;
        for(Integer num1 : List3){
            first = num1;
        }
        if (List3 != null) {
            for(int i = 0; i < table2.size(); i++) {
                List2 = table2.get(i);
                for (String name2 : List2) {
                    m++;
                    for (Integer num2 : List3) {
                        if((m < first) || (m == num2 && num2 > first)) {
                            List2.remove(name2);
                        }
                    }
                }
                m = 0;
            }
        }
        return table2;
    }

    /**
     * Effect: Add elements from list to List.
     * Return: The combined List.
     */
    public static LList<String> add(LList<String> List, LList<String> list) {
        for (String strl : list) {
            List.append(strl);
        }
        return List;
    }

    /**
     * Effect: Print a usage message to standard error.
     */
    public static void usage() {
        System.err.println("Usage: a3.Main <file1.csv> <file2.csv>");
    }

    /**
     * Write the main method, which parses the command line arguments, reads CSV files
     * into tables, and prints out the resulting table resulting from a left join of the
     * input tables. Hint: use helper methods.
     */
    public static void main(String[] args) {
        LList<LList<String>> example1 = csvToList("example1.csv");
        LList<LList<String>> example2 = csvToList("example2.csv");
        LList<LList<String>> examplejoin = join(example1,example2);
        LList<LList<String>> edit = edit2(example1,example2);
        for(LList<String> LList : examplejoin){
            System.out.println(cvsString(LList));
        }
        System.out.println("\n");
        LList<LList<String>> states1 = csvToList("states1.csv");
        LList<LList<String>> states2 = csvToList("states2.csv");
        //LList<LList<String>> statesjoin = join(states1, states2);
        //for (LList<String> LList : statesjoin) {
        //    System.out.println(cvsString(LList));
        //}
        //System.out.println("\n");
        //System.out.println(edit2(example1,example2));
    }

    /**
     * Effect: Change the output into a cvs style.
     * Return: The content of linked list in a format of suitable output.
     */
    public static String cvsString(LList<String> a) {
        StringBuilder sb = new StringBuilder();
        for(String b : a) {
            sb.append(b+",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
