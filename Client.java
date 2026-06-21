import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // User enters server IP
            System.out.print("Enter Server IP: ");
            String ip = sc.nextLine();

            // User enters port number
            System.out.print("Enter Port Number: ");
            int port = sc.nextInt();

            sc.nextLine();

            // Connect to server
            Socket socket = new Socket(ip, port);

            System.out.println("Connected to server!");

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

            while (true) {

                System.out.print("\nEnter Integer Number (or Exit): ");
                String message = sc.nextLine();

                // CLIENT SIDE VALIDATION
                if (!message.equalsIgnoreCase("Exit")) {

                    try {
                        Integer.parseInt(message);

                    } catch (NumberFormatException e) {

                        System.out.println("Invalid Input! Enter integer only.");
                        continue;
                    }
                }

                // Send to server
                output.println(message);

                // Exit condition
                if (message.equalsIgnoreCase("Exit")) {

                    String response = input.readLine();

                    System.out.println(response);

                    break;
                }

                // Receive results
                String factorial = input.readLine();
                String fibonacci = input.readLine();
                String digitSum = input.readLine();

                // Display results
                System.out.println(factorial);
                System.out.println(fibonacci);
                System.out.println(digitSum);
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}