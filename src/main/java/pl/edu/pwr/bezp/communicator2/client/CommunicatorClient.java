package pl.edu.pwr.bezp.communicator2.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.*;
import pl.edu.pwr.bezp.communicator2.actions.response.*;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

@Component
public class CommunicatorClient extends ServerAuthorizationLayer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommunicatorClient.class);

    public CommunicatorClient(CipherUtility cipherUtility) {
        super(cipherUtility);
        var keyPair = cipherUtility.getKeyPair();
        clientPrivateKey = keyPair.getPrivate();
        clientPublicKey = keyPair.getPublic();
//        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
//        root.setLevel(ch.qos.logback.classic.Level.DEBUG);
        System.out.println(LOGGER.isDebugEnabled());
    }

    @PostConstruct
    void init() throws Exception {
        try {
            getSessionPort();
            makeHandShake();
//            makeClientLogOrReg();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e + "");
        }
    }

//    private void makeClientLogOrReg() {
//        while (true) {
//            System.out.println("""
//                    Dostępne opcje:
//                    1.Rejestracja
//                    2.Logowanie
//                    Twój wybór:\s""");
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//                var input = reader.readLine();
//                System.out.println("Rozpoczecie procesu " + (input.equals("1") ? " rejestracji" : "logowania") + "!\n");
//                String login, password;
//
//                RespAbstract serverResp;
//                switch (input) {
//                    case "1" -> {
//                        serverResp = new RespRegistration(sendAction(new ActionRegistration(login, password).make()));
//                        if (serverResp.getResponseStatus() == ResponseStatus.OK) {
//                            serverResp = new RespLogin(sendAction(new ActionLogin(login, password).make()));
//                            if (serverResp.getResponseStatus() == ResponseStatus.OK) {
//                                makeClientRestOfActions(reader);
//                            }
//                        }
//                    }
//                    case "2" -> {
//                        serverResp = new RespLogin(sendAction(new ActionLogin(login, password).make()));
//                        if (serverResp.getResponseStatus() == ResponseStatus.OK) {
//                            makeClientRestOfActions(reader);
//                        }
//                    }
//                    default -> throw new IllegalStateException("Unexpected value: " + input);
//                }
//                System.out.print("Próbla " + (input.equals("1") ? " rejestracji" : "logowania") + " nieudana. Powód: ");
//                System.out.println(serverResp.getResponseText());
//                makeClientLogOrReg();
//                break;
//            } catch (IllegalStateException e) {
//                System.out.println("Brak takiej opcji!");
////                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (Exception e) {
//                LOGGER.error(String.valueOf(e));
//                //throw new RuntimeException(e);
//            }
//        }
//    }

//    private String sendAction(String actionToSend) {
//        try {
//            var encryptedMsg = /*cipherUtility.encrypt(*/actionToSend/*, serverPublicKey)*/;
//            out.println(encryptedMsg);
//            return /*cipherUtility.decrypt(*/in.readLine()/*, clientPrivateKey)*/;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private void makeClientRestOfActions(BufferedReader reader) throws IOException {
//        var stopLoop = true;
//        while (stopLoop) {
//            System.out.println("""
//                    Dostępne opcje:
//                    1.Utworzenie konwersacji
//                    2.Wylistowanie listy konwersacji
//                    3.Wyświtlenie wiadomości w wybranej konwersacji
//                    4.Wysłanie wiadomości w wybranej konwersacji
//                    5.Wyświetlanie dostępnych użytkowników
//                    Twój wybór:\s""");
//
//            var input = reader.readLine();
//            RespAbstract resp;
//            switch (input) {
//                case "1" -> {
//                    resp = new RespCreateConversation(sendAction(createConversation(reader).make()));
//                }
//                case "2" -> {
//                    var respListUsers = new RespListConversations(sendAction(new ActionListConversations().make()));
//                    System.out.println("Lista konwersacji: ");
//                    System.out.println(respListUsers.getConversations());
//                }
//                case "3" -> {
//
//                    var respMessageInConversation = new RespGetMessages(sendAction("s"/*new ActionGetMessagesFromConversation("name").make()*/));
//                    System.out.println("Wiadomo");
//                }
//                case "4" -> {
//
//                }
//                case "5" -> {
//                    var respListUsers = new RespListUsers(sendAction(new ActionListUsers().make()));
//                    System.out.println("Lista dostępnych klientów: ");
//                    System.out.println(respListUsers.getUsers());
//                }
//                case  "6" -> {
//                    stopLoop = false;
//                }
//            }
//        }
//
//
//    }
//
//    private ActionCreateConversation createConversation(BufferedReader reader) {
//        try {
//            System.out.println("Wprowadź nazwę konwersacji: ");
//            var conversationName = reader.readLine();
//            var availableUserList = new RespListUsers(sendAction(new ActionListUsers().make()));
//            var addedUserList = new ArrayList<String>();
//            var finishAddingUsers = true;
//            do {
//                System.out.println("""
//                        Dodawanie użytkowników do konwersacji
//                        Lista dodanych użytkowników:
//                        """);
//                System.out.println(addedUserList);
//                System.out.println("Lista dostępnych użytkowników:");
//                int i =0;
//                for (String user : availableUserList.getUsers()) {
//                    System.out.println(i+". "+user);
//                    i++;
//                }
//                System.out.println(availableUserList.getUsers());
//                System.out.println("""
//                        Dostępne opcje:
//                        1.Zakończ dodawanie użytkowników
//                        2.Dodaj wybranego użytkownikaużytkownika
//                        Twój wybór:\s""");
//                switch (reader.readLine()) {
//                    case "1" -> finishAddingUsers = false;
//                    case "2" -> {
//                        System.out.println("Wprowadz nr uzytkownika: ");
//                        addedUserList.add(availableUserList.getUsers().get(Integer.parseInt(reader.readLine())));
//                    }
//
//                }
//            } while (finishAddingUsers);
//            return new ActionCreateConversation(conversationName,addedUserList);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}
