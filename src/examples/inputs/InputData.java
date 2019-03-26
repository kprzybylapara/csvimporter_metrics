package examples.inputs;

/**
 * InputData
 * 
 * @author staff
 */
public class InputData {

    /**
     * This method adds two 4-bit non-negative integers (0..15) and returns the 4-bit
     * positive integer result (0..15).
     * 
     * @author staff
     * @param x
     *            4-bit non-negative integer x (0..15)
     * @pre x >= 0 && x < 16
     * @param y
     *            4-bit non-negative integer y (0..15)
     * @pre y >= 0 && y < 16
     * @return 4-bit non-negative integer result (0..15)
     * @post $result >= 0 && $result < 16
     * @exception IllegalArgumentException
     *                if result is not 4-bit non-negative integer (0..15)
     */
    public static int add(int x, int y) {
        int result = x + y;
        if (result <= 0 || result > 16) // BUG: should be: result < 0 || result >= 16
            throw new IllegalArgumentException(
                    "result is not 4-bit non-negative integer (0..15)"); //$NON-NLS-1$
        return x; // BUG: should be: result (not x)
    }
    
    public static int subtract (int x, int y){
    	return x-y;
    }
}