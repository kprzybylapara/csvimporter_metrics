package dbc;

public class Example {
    public static void main (String[] args) {
        try {
            printMonth (1);
            printMonth (30);
            printMonth (2);
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    /** @pre month >== 1 && month <= 12 */
    // DBC syntax error example, replace >== with >= to fix.
    private static void printMonth (int month) {
        System.out.println ("month = " + month);
    }
}