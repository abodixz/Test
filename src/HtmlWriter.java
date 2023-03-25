import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlWriter implements AutoCloseable  {
    private final BufferedWriter writer;

    private HtmlWriter(String filename) throws IOException {
        FileWriter fWriter = new FileWriter(filename);
        writer = new BufferedWriter(fWriter);
    }

    static public HtmlWriter CreateWriter(String filename) throws IOException {
        return new HtmlWriter(filename);
    }

    public void writeElement(String element) throws IOException {
        writer.write(element.toString());
        writer.newLine();
    }

    public void close() throws IOException {
        writer.close();
    }
}