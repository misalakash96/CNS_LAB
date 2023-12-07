import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAExample{
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    public RSAExample(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength, random);
        modulus = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        publicKey = new BigInteger("65537"); // Common public exponent
        privateKey = publicKey.modInverse(phi);

        System.out.println("RSA Key Generation:");
        System.out.println("p (prime number 1): " + p);
        System.out.println("q (prime number 2): " + q);
        System.out.println("n (modulus): " + modulus);
        System.out.println("f(n) (Euler's totient function): " + phi);
        System.out.println("Public Key (e): " + publicKey);
        System.out.println("Private Key (d): " + privateKey);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    public static void main(String[] args) {
        int bitLength = 512; // Adjust the bit length as needed
        RSAExample rsa = new RSAExample(bitLength);

        String message = "Hello, RSA!";
        BigInteger originalMessage = new BigInteger(message.getBytes());

        System.out.println("Original Message: " + message);
        System.out.println("Original Message (as BigInteger): " + originalMessage);

        BigInteger encryptedMessage = rsa.encrypt(originalMessage);
        System.out.println("Encrypted Message: " + encryptedMessage);

        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        String decryptedText = new String(decryptedMessage.toByteArray());
        System.out.println("Decrypted Message: " + decryptedText);
    }
}