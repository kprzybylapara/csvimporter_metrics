package examples.unit;

/**
 * Demonstrates five categories of testing tasks: exceptions, failures,
 * unverified exceptions, unverified exceptions, and unverified outcomes.
 * <p>
 * Directions:
 * </p>
 * <p>
 * First, run the "Example Configuration" Jtest configuration on this file.
 * Next, change the return value to 0 and run the "Run Unit Tests" Jtest
 * configuration to demonstrate failures. Finally, in the Errors Found view, try
 * verifying some of the unverified exceptions, unverified failures, and
 * unverified outcomes.
 * </p>
 */
public class TestingTasks {
    /**
     * Finds the first (lowest-index) empty string in an array of strings.
     * 
     * @param strings
     *            array of strings to search in or <code>null</code> for no
     *            strings. Any null elments in the array are ignored.
     * @return lowest index of an empty string in the array or <code>-1</code>
     *         if no empty string was found
     */
    public static int findEmpty(String[] strings) {
        // the next lines would fix the first NullPointerException
        // if (strings == null)
        //     return -1;
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            // the next lines would fix the second NullPointerException
            // if (string == null)
            //     continue;
            if (string.length() == 0)
                return i;
        }
        // change the return value to 0 to demonstrate failures
        // return 0;
        return -1;

    }
}