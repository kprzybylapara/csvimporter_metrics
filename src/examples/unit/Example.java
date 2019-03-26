package examples.unit;

/**
 * Example class to use for generating JUnit test cases.
 */
public class Example {
    /**
     * Allocates an array of the given size.
     * @param size
     * @return
     */
    public static int[] allocate(int size) {
        /*
        if (size < 0)
            return new int[0];
        */
        return new int[size];
    }

    /**
     * Adds two integers 'a' and 'b'.
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        return a + b;
    }

    /**
     * Empty method that accepts a byte, String, and double as parameters.
     * @param b
     * @param s
     * @param d
     */
    public static void method(byte b, String s, double d) {
    }

    /**
     * Method that maps certain arbitrary values to other arbitrary values. 
     * @param arg
     * @return
     */
    public static int method(int arg) {
        if (arg == 4)
            return 7;
        else if (arg == 145)
            return 9;
        else if (arg < -7543)
            return -1;
        else
            return 0;
    }
    
    /**
     * Call methods in this class to demonstrate
     * Jtest recorder feature
     * @param args
     */
    public static void main(String[] args) {
        
        int[] intArray = allocate (5);
        intArray = allocate (29);
        
        int result = add (5, 7);
        result = add (13, 17);
        result = add (19, 23);
        
        method ((byte)5, "a", 27.31);
        method ((byte)3, "b", 11.39);
        
        method (3);
        method (-111111);
    }
}