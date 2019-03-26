package examples.flowanalysis;
import java.util.logging.*;

public class AlwaysCloseLogging
{
    public void log(Level level, String message)
    {
        ConsoleHandler handler = new ConsoleHandler();
        LogRecord record = new LogRecord(level, message);
        handler.publish(record);
    }

    public void logClose(Level level, String message)
    {
        ConsoleHandler handler = null;
        try {
            handler = new ConsoleHandler();
            LogRecord record = new LogRecord(level, message);
            handler.publish(record);
        } finally {
            handler.close();
        }
    }
}
