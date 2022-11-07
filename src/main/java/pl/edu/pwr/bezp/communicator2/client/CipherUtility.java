package pl.edu.pwr.bezp.communicator2.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

@Component
public class CipherUtility {

    private static KeyPairGenerator keyPairGenerator = null;
    private final SecureRandom random = new SecureRandom();
    @Value("${size.kyeSize}")
    public int KEY_SIZE;

    public KeyPair getKeyPair() {
        return keyPairGenerator.genKeyPair();
    }

    private String encrypt(byte[] contentBytes, Key pubKey) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //byte[] contentBytes = content.getBytes();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherContent = cipher.doFinal(contentBytes);
        String encoded = Base64.getEncoder().encodeToString(cipherContent);
        return encoded;
    }

    String encrypt(String text, Key publicKey) throws Exception {
        var textBytes = text.getBytes(StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (text.getBytes().length > 100 * (i + 1)) {
            var toEncrypt = Arrays.copyOfRange(textBytes, i * 100, (i + 1) * 100);
            result.append(encrypt(toEncrypt, publicKey));
            i++;
        }
        result.append(encrypt(Arrays.copyOfRange(textBytes, 100 * (i), textBytes.length), publicKey));
        return result.toString();
    }

    String decrypt(String text, Key privateKey) throws Exception {
        var textBytes = text.getBytes(StandardCharsets.UTF_8);
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (textBytes.length > 172 * i + 1) {
            var toDecrypt = Arrays.copyOfRange(textBytes, i * 172, (i + 1) * 172);
            result.append(decrypt(toDecrypt, privateKey));
            i++;
        }
//        result.append(decrypt(Arrays.copyOfRange(textBytes, 172 * (i ), textBytes.length), privateKey));
        return result.toString();
    }

    private String decrypt(byte[] cipherContent, Key privKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] cipherContentBytes = Base64.getDecoder().decode(cipherContent/*.getBytes()*/);
        byte[] decryptedContent = cipher.doFinal(cipherContentBytes);

        return new String(decryptedContent);
    }

    public String encodeKey(Key key) {
        byte[] keyBytes = key.getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }

//    public PublicKey decodePublicKey(String keyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
//        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        PublicKey key = keyFactory.generatePublic(spec);
//        return key;
//    }

    public PublicKey decodePublicKey(String keyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(spec);
        return key;
    }

    public PrivateKey decodePrivateKey(String keyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Base64.getDecoder().decode(keyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        return key;
    }

    @PostConstruct
    public void init() {
        try {
            if (keyPairGenerator == null) keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE, random);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}