package pl.edu.pwr.bezp.communicator2.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component
public class SocketsConnectionLayer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketsConnectionLayer.class);
    @Value("${client.port}")
    public int SERVER_PORT;
    protected PrintWriter out;
    protected BufferedReader in;
    private Socket clientSocket;
    @Value("${client.address}")
    private String ip;

    private int sessionPort;

    @PostConstruct
    void init() throws Exception {
        getSessionPort();
        Thread.sleep(1000);
        startConnection(ip, sessionPort);
        LOGGER.info("Start connection to ip: " + ip + " on port: " + sessionPort);
    }

    protected void getSessionPort() {
        //get session port
        try {
            startConnection(ip, SERVER_PORT);
            sessionPort = Integer.parseInt(sendMessage("Hi"));
            LOGGER.info("Session port is: " + sessionPort);
            stopConnection();

        } catch (IOException e) {
            LOGGER.error("Server isn't running", e);
        }
    }

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
