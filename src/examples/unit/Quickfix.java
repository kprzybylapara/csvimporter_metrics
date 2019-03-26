package examples.unit;

/**
 * Example class for "quickfix" feature
 * Generate and run tests for this class and select the "Quickfix"
 * 
 * @author staff
 */
public class Quickfix {
    Quickfix (String arg) {
        _arg = arg;
    }
    
    public int getLength () {
        return _arg.length ();
    }
    
    public static int sgetLength (String str) {
        return str.length ();
    }
    
    private String _arg;
}