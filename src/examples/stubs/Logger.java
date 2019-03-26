package examples.stubs;

public class Logger {

    public void sendMessage() {
        System.out.println ("inside Logger");
        throw new RuntimeException ();
    }
}