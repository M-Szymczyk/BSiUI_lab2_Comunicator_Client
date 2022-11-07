package pl.edu.pwr.bezp.communicator2.client;

import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ServerAuthorizationLayer /*extends SocketsConnectionLayer*/ {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerAuthorizationLayer.class);
    protected final CipherUtility cipherUtility;
    @Value("${client.port}")
    public int SERVER_PORT;
    protected PublicKey clientPublicKey;
    protected PrivateKey clientPrivateKey;
    private int sessionPort;
    @Value("${client.address}")
    private String ip;
    private PublicKey serverPublicKey;

    private SocketsConnectionLayer sockets;

    public ServerAuthorizationLayer(CipherUtility cipherUtility) {
        this.cipherUtility = cipherUtility;
    }

    protected void getSessionPort() throws IOException {
        //get session port
        try {
            sockets = new SocketsConnectionLayer(ip, SERVER_PORT);
            sessionPort = Integer.parseInt(sockets.sendMessage("Hi"));
            LOGGER.info("Session port is: " + sessionPort);
            sockets.stopConnection();
        } catch (IOException e) {
            LOGGER.error("Server isn't running", e);
        }

    }

    protected void makeHandShake() throws Exception {
        try {
            //connect to new port
            Thread.sleep(1000);
            new SocketsConnectionLayer(ip, sessionPort);
            LOGGER.info("Start connection to ip: " + ip + " on port: " + sessionPort);
            Thread.sleep(1000);

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
            var enctasd = cipherUtility.encrypt("auth", serverPublicKey);
            //check if it is good server
            var fAuth = cipherUtility.decrypt(sockets.sendMessage(enctasd), clientPrivateKey);

            var encodedHash = Hashing.sha256().hashString(
                    "auth" + cipherUtility.encodeKey(clientPublicKey),
                    StandardCharsets.UTF_8).toString();
            if (!fAuth.equals(encodedHash)) {
                sockets.stopConnection();
                LOGGER.error("Server is corrupted");
                return;
            }
            LOGGER.info("Successful handshake with server");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
