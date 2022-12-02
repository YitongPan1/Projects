/** Please provide the following information
 * Name(s): Yitong Pan
 * NetID(s): yp258
 * Tell us what you thought about the assignment: It is harder than I expected
 */

/** The goal of this assignment is to familiarize yourself with Java and start
 * to establish good programming practices.
 */

import javax.lang.model.type.NullType;

/** Class A1 defines a set of methods to implement. Each method has a comment
 * at the top. These are method specifications (specs) Method specs allow
 * anyone who is reading the program to immediately understand what the method
 * is doing.
 *
 * Each function body has in it a return statement. Without it, the function
 * won't compile. Replace the return statement with code you write to implement
 * the spec.
 */
public class A1 {

    /** Replace the "-1" with the amount of time you spent on A1 in hours.
     *  Example: for 1 hour and 30 min, write that timeSpent = 1.5
     *  Example: for 1 hour, write that timeSpent = 1 or timeSpent = 1.0
     */
    public static double timeSpent = 6.0;

    /** Return the product of the values: 7, 11, and 13. */
    public static int product() {
        return 7*11*13;
    }

    /** In the following order: double x, add 4, divide it by 2, and then
     *  subtract the original x value from the result. */
    public static int theAnsweris2(int x){
        return (x*2+4)/2-x;
    }

    /** If x is not a three-digit number, return -1;
     * Otherwise return the product of x and the values: 7, 11, and 13.
     */
    public static int magicTrick(int x) {
        if(x/100 != 0 && x<1000 && x > -1000){
            return x*7*11*13;
        }
        else {
            return -1;
        }
    }

    /** Given some temperature x of water in degrees Celsius,that the
     * melting point of water is 0ºC , and that the boiling point is 100ºC,
     * determine if the water is liquid. Note: in our program water is not
     * a liquid at 0ºC or 100ºC.
     */
    public static boolean isLiquid(int temperature) {
        return temperature > 0 && temperature < 100;
    }

    /** Given some value x, return 42 if x is equal to 42. If not, return -1.
     */
    public static int theAnsweris42Conditional(int x){
        return (x == 42) ? 42: -1;
    }

    /** Given two triangle legs a and b of a right triangle, return the hypotenuse.
     *  Requires: a and b must be positive values.
     */
    public static double hypotenuse(double a, double b) {
        return Math.sqrt(a*a + b*b);
    }

    /** Given three triangle side lengths a, b, and c, determine if the triangle can exist.
     */
    public static boolean isTriangle(double a, double b, double c) {
        return a+b>c && a+c>b && b+c>a;
    }

    /** Given four operations: ADD, SUBTRACT, MULTIPLY, and DIVIDE and two inputs x and y,
     *  return the result of the operation between x and y.
     *  Requires: opp = DIVIDE and y = 0 cannot be true at the same time.
     */
    public static int calculate(String opp, int x, int y) {
        if(opp.equals("ADD")) {
            return x+y;
        }
        if(opp.equals("SUBTRACT")){
            return x-y;
        }
        if(opp.equals("MULTIPLY")){
            return x*y;
        }
        if(opp.equals("DIVIDE") && y != 0){
            return x/y;
        }
        else {
            return 1/0;
        }
    }

    /** Return the sum of the values in n to m inclusive.
     */
    public static int rangeSum(int n, int m){
        int sum = 0;
        for (int i = n; i<m+1; i++){
            sum += i;
        }
        return sum;
    }

    /** Return the sum of the odd values in n to m inclusive.
     */
    public static int rangeSumOdd(int n, int m) {
        int sum = 0;
        int i = n;
        while (i<m+1){
            if(i%2 == 1){
                sum += i;
                i++;
            }
            else{
                i++;
            }
        }
        return sum;
    }

    /** Return whether str is a palindrome.
     */
    public static boolean isPalindrome(String str) {
        int n = str.length();
        int x = 0;
        for(int i = 0; i < n; i++){
            if(str.charAt(i) != str.charAt(n-i-1)){
                x++;
            }
        }
        return x == 0;
    }

    /** Return a String that return a string that contains the
     *  same characters as str, but with each vowel duplicated.
     */
    public static String ins(int index, String s, String old){
        int c = old.length();
        return old.substring(0, index) + s + old.substring(index, c);
    }
    public static String duplicateVowels(String str) {
        String newstring = str;
        String vowel = "aeiou";
        for (int j = 0; j < 5; j++) {
            Character abc = vowel.charAt(j);
            if (newstring.indexOf(abc) == -1) {
                newstring = newstring;
            }
            else {
                for (int i = 0; i < newstring.length(); i++) {
                    if (newstring.charAt(i) == abc) {
                        newstring = ins(i+1, new String(String.valueOf(abc)), newstring);
                        i += 1;
                    }
                }
            }
        }
    return newstring;
    }
}