package pl.edu.pwr.bezp.communicator2.client.crytoUtilsi;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class AES {
    private String aesKey;

    @PostConstruct
    private void init() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            SecretKey key = keyGenerator.generateKey();
            aesKey = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public String encrypt(String data) throws Exception {
        Cipher encryptionCipher = Cipher.getInstance("AES");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKeyFromString(aesKey));
        byte[] encryptedMessageBytes =
                encryptionCipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedMessageBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        Cipher decryptionCipher = Cipher.getInstance("AES");
        decryptionCipher.init(Cipher.DECRYPT_MODE, secretKeyFromString(aesKey));
        byte[] decryptedMessageBytes =
                decryptionCipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedMessageBytes);
    }

    private SecretKey secretKeyFromString(String key) {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public String getAesKey() {
        return aesKey;
    }
}
