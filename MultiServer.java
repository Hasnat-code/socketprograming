import java.net.*;

public class MultiServer {

    public static void main(String[] args) {

        try {

            int port = 5000;

            ServerSocket serverSocket =
                    new ServerSocket(port);

            System.out.println(
                    "Server Started on Port: " + port
            );

            while (true) {

                // Accept client
                Socket socket =
                        serverSocket.accept();

                System.out.println(
                        "New Client Connected!"
                );

                // Create thread
                ClientHandler client =
                        new ClientHandler(socket);

                // Start thread
                client.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}