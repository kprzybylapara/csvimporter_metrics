
package examples.runtime.security;

/**
 * Example for UserMethodSpecifier.
 */
public class MethodSpecifierExample {
    
    public static void main(String[] args) {
        String input_1 = generateData("a");
        String input_2 = generateData("b");
        
        // only validates input_1
        verifyInputIsGood(input_1);
        
        dangerousMethod(input_1);
        dangerousMethod(input_2);
    }
    
    /**
     * We will use the UserMethodSpecifier to specify that this method generates
     * tainted data.
     */
    private static String generateData(String arg) {
        return "generated_data: " + arg;
    }
    
    /**
     * Validation name that doesn't match the default validation* pattern. Will
     * need to use UserMethodSpecifier to specify that this method validates the
     * input.
     */
    private static void verifyInputIsGood(String input) {
        if ("bad input".equals(input))
            throw new IllegalArgumentException("bad input: " + input);
    }
    
    /**
     * No tainted data should be passed to this method. UserMethodSpecifier
     * specifies that this method is dangerous.
     */
    private static void dangerousMethod(String input) {
        System.out.println("dangerousMethod: " + input);
    }
}
