/**
 * Lab6: Complete the recursive method isaPalindrome
 *
 */

import java.util.Scanner;

public class Lab6{


    public static void main(String[] args){
	/******************************************************** Palindrome */
	if (!isaPalindrome("ratsliveonnoevilstar"))
	    System.out.println("Failed to recognize a palindrome!");
	else
	    System.out.println("Successful check for palidrome ok");
			    
	Scanner console = new Scanner(System.in);
	System.out.print("Input a string\n>");
	String line = console.nextLine();
	while (!line.equals("")){
	    if (isaPalindrome(line))
		System.out.println(line+ "is a palindrome");
	    else
		System.out.println(line+ "is *not* a palindrome");
	    System.out.print("Input a string\n >");
	    line = console.nextLine();
	}
    }

    /** method to determine whether the specified string is a palindrome
     * @param expr string 
     * @return true if the specified string is a palindrome, otherwise false.
     */
    
    public static boolean isaPalindrome(String expr){
	// TO COMPLETE
        if (expr.length() <= 1) {
            return true;
        }
        else {
            if (expr.charAt(0) != expr.charAt(expr.length()-1)) {
                return false;
            }
            else {
                expr = expr.substring(1, expr.length()-1);
                return isaPalindrome(expr);
            }
            
        }
    }
    
}