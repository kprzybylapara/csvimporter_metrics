
package examples.runtime.security;

import java.io.IOException;

import examples.runtime.ExamplesUtil;

/**
 * Example for UserInputValidator.
 */
public class InputValidatorExample {
    
    public static void main(String[] args) throws IOException {
        String path = ExamplesUtil
            .prompt("Enter library path (i.e. \\lib_hacked;\\lib)");
        // VIOLATION: tainted data used to set a system property
        System.setProperty("java.library.path", path);
    }
}
