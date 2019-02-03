import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * A command line client for the date server. It prompts you, at the console, to enter
 * the IP address of a server, then displays the response from the server on success,
 * otherwise it crashes and dumps the exception trace.
 */
public class DateClient {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter the IP address of a machine running the date server:");
        String serverAddress = new Scanner(System.in).nextLine();
        Socket socket = new Socket(serverAddress, 9090);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response = in.readLine();
        System.out.println("Server response: " + response);
    }
}