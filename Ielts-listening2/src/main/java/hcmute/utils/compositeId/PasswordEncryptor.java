package hcmute.utils.compositeId;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryptor {
	private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final int KEY_SIZE = 128;
    private static final String SECRET_KEY = "MySecretKey12345"; 

    public static String encryptPassword(String password) {
        try {
            SecretKey secretKey = generateSecretKey();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedBytes = cipher.doFinal(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptPassword(String encryptedPassword) {
        try {
            SecretKey secretKey = generateSecretKey();
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static SecretKey generateSecretKey() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] keyBytes = new byte[KEY_SIZE / 8]; // Chuyển đổi độ dài khóa từ bit sang byte
            secureRandom.nextBytes(keyBytes);
            return new SecretKeySpec(keyBytes, ALGORITHM);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
