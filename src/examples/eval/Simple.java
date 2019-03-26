/*
 * @author staff
 *
 * This is a class showcasing the ability of Jtest.  This code 
 * has a number of Coding Standards errors developers commonly make. 
 * Also, it has a public method which causes software exceptions.  
 * 
 * Methods in the public api should never throw unexpected 
 * exceptions.
 * 
 * If the code is meant to handle invalid arguments which cause
 * software exceptions then the code should be fixed accordingly and
 * should not have software exceptions.
 * 
 * If the exceptions are expected, then the code should document 
 * this with @throws or @exception.
 * 
 * If the code is not meant to handle invalid arguments which will
 * cause exceptions (such as null, or zero length strings) and the 
 * responsibility is on the caller of the method then Design by 
 * Contract should be used to explicitly document these cases.
 * 
 * Jtest's automatic generation of test cases help developers realize
 * method arguments that cause problems in the code.
 * 
 * Jtest considers that coding standards violations and software 
 * exceptions are bugs and reports them and the conditions that cause
 * them to the developer.
 *
 */

package examples.eval;


/**
 * A class containing some common coding standards violations 
 * and software exceptions.
 * 
 * This is a utility class and is not meant to be instantiated.
 */
public class Simple
{
    /**
     * Takes an index, and returns the appropriate map for the index.
     * 
	 * @param index
	 * @return map for the index
	 */
	public static int map (int index) {
        switch (index) {
        case 0:
        case10:
            return -1;
        case 2:
        case 20:
        	break;
        default:
            return -2;
        }
        return 0;
    }

    /**
     * Takes two arguments and returns the boolean result if 'str' 
     * starts with the string, 'match'.
     * 
     * This method throws NullPointerExceptions when either argument
     * to the method is null -- this may or may not be by design.
     * 
     * 'startsWith' also throws a StringIndexOutOfBoundsException 
     * when 'str' is a string with less length than 'match' - this is
     * most definitely a software exception.
     * 
     * A method should be able to handle null arguments gracefully, 
     * declare preconditions that the method does not expect null 
     * arguments from the caller, or in the least, it should document
     * that nullpointer exceptions are possible. 
     * 
     * Developers must consider the cases when exceptions could happen 
     * in the code.  Especially code in the public api.  
     * 
	 * @param str the string we want to search in
	 * @param match the substring we're searching for
	 * @return true if 'str' starts with 'match', false otherwise.
	 * 
	 * Finding the set of arguments that cause software exceptions
	 * is hard if done by hand but is easy for Jtest.  It will
	 * test tirelessly and will never get bored of testing.
	 */
    
    /*
     * Place the dbc below into the javadoc comment above to 
     * explicitly declare this method does not handle null arguments.  
     * Jtest will not call the method with null arguments if the pre-
     * conditions exist.
     *    
     * @pre str != null && match != null
     * 
     * Note: DbC must be enabled for preconditions to take effect.
     * 
     * Preconditions assume responsibility is on the caller with regards
     * to validating method arguments on the called method.
     */
	public static boolean startsWith (String str, String match) {
        for (int i = 0; i < match.length (); ++i)
            if (str.charAt (i) != match.charAt (i))
                return false;
        return true;
    }

    /**
     * Takes two arguments and returns their sum.  
     * 
     * This is a example of automatic regression testing.  Try gen-
     * erating test cases for this code then change the summation 
     * in the code below to a subtraction and you'll see a regression 
     * error.
     * 
     * A regression error happens when the code returns current results
     * different from previous results.  When a regression error happens, 
     * a judgement needs to be made to determine if the regression error 
     * is expected.  If it is expected, then the developer should update
     * the test case to reflect the new results.
     * 
	 * @param i1
	 * @param i2
	 * @return the sum of 'i1' and 'i2'
	 */
	public static int add (int i1, int i2) {
        return i1 + i2;
    }   
}