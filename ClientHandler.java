import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        try {

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()
                    )
            );

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

            String clientIP =
                    socket.getInetAddress().toString();

            while (true) {

                // Receive message
                String data = input.readLine();

                if (data == null) {
                    break;
                }

                // Exit condition
                if (data.equalsIgnoreCase("Exit")) {

                    output.println("Connection closed.");

                    break;
                }

                try {

                    int number = Integer.parseInt(data);

                    // Validate positive integer
                    if (number <= 0) {

                        output.println(
                                "Error: Please enter positive integer."
                        );

                        output.println(" ");

                        continue;
                    }

                    // Log client IP and number
                    System.out.println(
                            "Client IP: "
                                    + clientIP
                                    + " | Number: "
                                    + number
                    );

                    // Find divisors
                    ArrayList<Integer> divisors =
                            new ArrayList<>();

                    int sum = 0;

                    for (int i = 1; i < number; i++) {

                        if (number % i == 0) {

                            divisors.add(i);

                            sum += i;
                        }
                    }

                    // Check perfect number
                    String result;

                    if (sum == number) {

                        result = "Perfect Number";

                    } else {

                        result = "Not a Perfect Number";
                    }

                    // Send result
                    output.println(
                            "Result: " + result
                    );

                    output.println(
                            "Proper Divisors: "
                                    + divisors
                    );

                } catch (NumberFormatException e) {

                    output.println(
                            "Error: Invalid input."
                    );

                    output.println(
                            "Please enter integer only."
                    );
                }
            }

            socket.close();

            System.out.println(
                    "Client disconnected: "
                            + clientIP
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}