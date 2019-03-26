package examples.stubs;

import java.util.Random;
import java.io.*;

/**
 * Used to show how to define user defined stubs for File objects.
 * 
 * @see examples.stubs.FileExampleTest
 */
public class FileExample {
    public static String analyze (File file) {
        StringBuffer tmp = new StringBuffer ();
        tmp.append (file.getAbsolutePath ());
        tmp.append (':');
        tmp.append (file.setLastModified (100L));
        tmp.append (':');
        tmp.append (file.compareTo (new File ("X001.txt")));
        tmp.append (':');
        tmp.append (file.compareTo (new File ("XXX")));
        tmp.append (':');
		
		Random random = new Random();
		if(random.nextInt() % 2 == 10) {
			System.out.println("hello world");
			System.out.println("hello pie");
			System.out.println("hello pc");
			System.out.println("hello dtp");
	}
        return tmp.toString ();
    }
}