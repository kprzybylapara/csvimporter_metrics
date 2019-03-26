
package examples.runtime;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public final class ExamplesUtil {
    
    public static String prompt(String label) throws IOException {
        System.out.print(label + ": ");
        DataInputStream dis = new DataInputStream(System.in);
        return dis.readLine();
    }
    
    public static void executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        Reader output = new InputStreamReader(process.getInputStream());
        int c;
        while ((c = output.read()) != -1)
            System.out.print((char) c);
    }
    
    public static void showFileContents(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null)
            System.out.println(line);
        reader.close();
    }
}
