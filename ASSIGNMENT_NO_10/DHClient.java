import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;

public class DHClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6666);

            // Step 1: Enter prime number (q)
            System.out.print("Enter prime number (q): ");
            Scanner scanner = new Scanner(System.in);
            BigInteger q = scanner.nextBigInteger();

            // Step 2: Enter primitive root (alpha)
            System.out.print("Enter primitive root (alpha): ");
            BigInteger alpha = scanner.nextBigInteger();

            // Step 3: Enter private key (Xb)
            System.out.print("Enter private key (Xb): ");
            BigInteger Xb = scanner.nextBigInteger();

            // Step 4: Calculate public key (Yb)
            BigInteger Yb = alpha.modPow(Xb, q);

            // Step 5: Send Yb to the server
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(Yb);

            // Step 6: Receive Ya from the server
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            BigInteger Ya = (BigInteger) in.readObject();

            // Calculate shared key (k)
            BigInteger k = Ya.modPow(Xb, q);

            System.out.println("Shared Key (k): " + k);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}