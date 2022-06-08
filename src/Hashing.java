import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {


    public final String getSecurePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // not recommended to use
            byte[] digest = md.digest(password.getBytes());
            final String hash = new BigInteger(1, digest).toString(16);

            return hash + getSalt();
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception);
        }
    }


    // ok, I will change code later
    public static int getSalt() {
        int max = 9999;
        int min = 1000;

        return (int) Math.floor(Math.random() * (max - min + 1) + min); // random number from min to max
    }

}
