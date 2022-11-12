package pl.edu.pwr.bezp.communicator2.client;

import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.AES;
import pl.edu.pwr.bezp.communicator2.client.crytoUtilsi.CipherUtility;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Random;

@Component
public class ServerAuthorizationLayer /*extends SocketsConnectionLayer*/ {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerAuthorizationLayer.class);
    private final CipherUtility cipherUtility;
    private final AES aes;
    private final SocketsConnectionLayer sockets;
    protected PublicKey clientPublicKey;
    protected PrivateKey clientPrivateKey;
    private PublicKey serverPublicKey;

    public ServerAuthorizationLayer(CipherUtility cipherUtility, AES aes, SocketsConnectionLayer sockets) {
        this.cipherUtility = cipherUtility;
        var keyPair = cipherUtility.getKeyPair();
        clientPrivateKey = keyPair.getPrivate();
        clientPublicKey = keyPair.getPublic();
        this.aes = aes;
        this.sockets = sockets;
    }

    @PostConstruct
    void init() throws Exception {
        try {
            makeHandShake();
            switchToAes();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e + "");
        }
    }

//    protected void getSessionPort() throws IOException {
//        //get session port
//        try {
//            sockets.startConnection(ip, SERVER_PORT);
//            sessionPort = Integer.parseInt(sockets.sendMessage("Hi"));
//            LOGGER.info("Session port is: " + sessionPort);
//            sockets.stopConnection();
//        } catch (IOException e) {
//            LOGGER.error("Server isn't running", e);
//        }
//
//    }

    private void makeHandShake() throws Exception {
        try {
            //send public key and receive server encrypted public key
            var clientPublicKeyStr = cipherUtility.encodeKey(clientPublicKey);
            LOGGER.info("Send client public key: " + clientPublicKeyStr);
            Thread.sleep(1000);

            //receive server public key
            var receivedKey = sockets.sendMessage(clientPublicKeyStr);
            LOGGER.info("Server public key received");
            serverPublicKey = cipherUtility.decodePublicKey(cipherUtility.decrypt(receivedKey, clientPrivateKey));
            LOGGER.debug("Server public key is: " + serverPublicKey);

            //send authentication word
            var auth = controlMessageGenerator(100);
            var fAuth = cipherUtility.decrypt(sockets.sendMessage(cipherUtility.encrypt(auth, serverPublicKey)), clientPrivateKey);

            //check if it is good server
            var encodedHash = Hashing.sha256().hashString(
                    auth+"a" + cipherUtility.encodeKey(clientPublicKey),
                    StandardCharsets.UTF_8).toString();
            if (!fAuth.equals(encodedHash)) {
                sockets.stopConnection();
                LOGGER.error("Server is corrupted");
                throw new RuntimeException("Server is corrupted");
            }
            LOGGER.info("Successful handshake with server");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToAes() {
        try {
            sockets.out.println(cipherUtility.encrypt(aes.getAesKey(), serverPublicKey));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String controlMessageGenerator(int length) {
        byte[] array = new byte[length];
        new Random().nextBytes(array);
        return Base64.getEncoder().encodeToString(array);
    }
}
