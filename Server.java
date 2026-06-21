import java.net.*;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // User enters port number
            System.out.print("Enter port number: ");
            int port = sc.nextInt();

            // Create server socket
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Server started on port " + port);

            while (true) {

                // Accept client
                Socket socket = serverSocket.accept();

                System.out.println(
                        "New client connected: "
                                + socket.getInetAddress()
                );

                // Create thread
                ClientHandler clientThread =
                        new ClientHandler(socket);

                // Start thread
                clientThread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}