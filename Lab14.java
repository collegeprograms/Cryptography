import java.security.*;
import java.util.Scanner;

public class lab14 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter message: ");
        byte[] message = sc.nextLine().getBytes("UTF-8");

        // 1. Create private and public keys
        KeyPairGenerator g = KeyPairGenerator.getInstance("DSA");
        g.initialize(1024);
        KeyPair keys = g.generateKeyPair();

        // 2. Sign using the private key
        Signature s = Signature.getInstance("SHA1withDSA");
        s.initSign(keys.getPrivate());
        s.update(message);
        byte[] sign = s.sign();

        // Display the signature
        System.out.print("Digital Signature: ");
        for (byte b : sign) {
            System.out.printf("%02x", b & 0xff);
        }
        System.out.println();

        // 3. Verify using the public key
        s.initVerify(keys.getPublic());
        s.update(message);
        System.out.println("Signature valid: " + s.verify(sign));

        sc.close();
    }
}
