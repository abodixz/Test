import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class Server {
    public static int PORT;
    private final String FILENAME;

    private Server(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public static Server createServer(String FILENAME) {
        return new Server(FILENAME);
    }


    void StartServer(int PORT) throws IOException {
        Server.PORT = PORT;
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server started. Listening on port " + PORT + "...");
        ServerLoop(server);

    }

    void ServerLoop(ServerSocket server) throws IOException {
        while (true) {
            Socket client = server.accept();

            new Thread(() -> {
                String response = "";
                try {
                    response = readHtmlFile();
                } catch (Exception e) {
                    // catch any exceptions here
                    System.out.println("Error");
                }

                try {
                    sendHttpResponse(client.getOutputStream(), response);
                    client.close();
                } catch (IOException e) {
                    // catch any exceptions here
                    System.out.println("Error");
                }

            }).start();
        }
    }


    public static void sendHttpResponse(OutputStream outputStream, String body) throws IOException {
        String response = """
                    HTTP/1.1 200 OK\r
                    Content-Type: text/html; charset=UTF-8\r
                    Connection: close\r
                    \r
                    """;
        response += body;
        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }


    // Åpne URL-en i standard nettleser
    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String readHtmlFile() throws Exception {
        File file = new File(FILENAME);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

}