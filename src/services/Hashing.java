package services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Hashing {

    public final String[] getSecurePassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // not recommended to use
            byte[] digest = md.digest(password.getBytes());
            final String hash = new BigInteger(1, digest).toString(16);

            String salt = getSalt();

            return new String[]{hash, salt};
        } catch (NoSuchAlgorithmException exception) {
            throw new RuntimeException(exception);
        }
    }


    // ok, I will change code later
    public static String getSalt() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
