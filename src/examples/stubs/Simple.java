package examples.stubs;

public class Simple {
    
    public static int add(int i1, int i2) {
        LOGGER.sendMessage();
        return i1 + i2;
    }
    
    public static final Logger LOGGER = new Logger ();
}