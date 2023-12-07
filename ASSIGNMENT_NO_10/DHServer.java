import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;

public class DHServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("Server: Waiting for a client to connect...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Server: Client connected.");

            // Step 1: Enter prime number (q)
            System.out.print("Enter prime number (q): ");
            Scanner scanner = new Scanner(System.in);
            BigInteger q = scanner.nextBigInteger();

            // Step 2: Enter primitive root (alpha)
            System.out.print("Enter primitive root (alpha): ");
            BigInteger alpha = scanner.nextBigInteger();

            // Step 3: Enter private key (Xa)
            System.out.print("Enter private key (Xa): ");
            BigInteger Xa = scanner.nextBigInteger();

            // Step 4: Calculate public key (Ya)
            BigInteger Ya = alpha.modPow(Xa, q);

            // Step 5: Send Ya to the client
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(Ya);

            // Step 6: Receive Yb from the client
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            BigInteger Yb = (BigInteger) in.readObject();

            // Calculate shared key (k)
            BigInteger k = Yb.modPow(Xa, q);

            System.out.println("Shared Key (k): " + k);

            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}