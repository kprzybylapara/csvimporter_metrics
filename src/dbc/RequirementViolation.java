package dbc;

/**
 * This class demonstrates Jtest finding a requirement violation using design by
 * contract.
 */
public class RequirementViolation {

    /**
     * @post $result == i + x
     */
    public static int add (int i, int x) {
        return 1 + x;
    }
}