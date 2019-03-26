package examples.repository;

/**
 * Demonstrates how using the object repository will increase coverage:<br>
 * 1) Run the Example configuration on this java file.<br>
 * 2) Note that the 'allocate()' method is not fully covered.<br>
 * 3) Add a 'Message' object to the Object Repository with a nonnull '_message'.<br>
 * 4) Delete the MessageTest.java file.
 * 5) Rerun the Example configuration on this java file.<br>
 * 6) Note that 'allocate()' is now fully covered and how the object<br>
 *    you put in the Object Repository is used in MessageTest.java.
 */
public class Message {

    public Message () {
    }

    public void resetMessage (String message) {
        _message = message;
    }

    public int[] allocate (int size) throws Exception {
        int len = size + _message.length ();
        return new int[len];
    }

    private String _message;
}