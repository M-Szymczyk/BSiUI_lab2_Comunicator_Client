package pl.edu.pwr.bezp.communicator2.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.edu.pwr.bezp.communicator2.actions.AbstractAction;
import pl.edu.pwr.bezp.communicator2.actions.response.RespAbstract;

import javax.annotation.PostConstruct;
import javax.naming.CommunicationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommunicatorClient  {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommunicatorClient.class);

    private final ServerAuthorizationLayer authorizationLayer;
    public static Map<String, AbstractAction> communicatorOptions;

    public CommunicatorClient( ServerAuthorizationLayer authorizationLayer) {
        this.authorizationLayer = authorizationLayer;
    }

    @Autowired
    public void setCommunicatorOptions(Map<String, AbstractAction> communicatorOptions) {
        CommunicatorClient.communicatorOptions = communicatorOptions;
    }

    @PostConstruct
    void init() throws Exception {
        try {
            authorizationLayer.getSessionPort();
            authorizationLayer.makeHandShake();
            authorizationLayer.switchToAes();
            makeClientLogOrReg();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e + "");
        }
    }

    private void makeClientLogOrReg() {
        while (true) {
            System.out.println("""
                    Dostępne opcje:
                    1.Rejestracja
                    2.Logowanie
                    Twój wybór:\s""");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                var input = reader.readLine();
                System.out.println("Rozpoczecie procesu " + (input.equals("1") ? " rejestracji" : "logowania") + "!\n");
                RespAbstract serverResp;
                switch (input) {
                    case "1" -> {
                        serverResp = performAction("register");
                        if (serverResp.getResponseStatus() == HttpStatus.OK) {
                            performAction("login");
                            makeClientRestOfActions(reader);
                            return;
                        }
                    }
                    case "2" -> {
                        serverResp = performAction("login");
                        if (serverResp.getResponseStatus() == HttpStatus.OK) {
                            makeClientRestOfActions(reader);
                            return;
                        }
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + input);
                }
                System.out.print("Próbla " + (input.equals("1") ? " rejestracji" : "logowania") + " nieudana. Powód: ");
                System.out.println(serverResp.getResponseText());
                makeClientLogOrReg();
                break;
            } catch (IllegalStateException e) {
                System.out.println("Brak takiej opcji!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                LOGGER.error(String.valueOf(e));
            }
        }
    }

    private RespAbstract performAction(String action) throws CommunicationException {
        if (communicatorOptions.containsKey(action)) {
            LOGGER.info("USER PERFORMED " + action + " WITH " /*+ message*/);
            return communicatorOptions.get(action).run();
        }
        throw new CommunicationException("No such action available");
    }

    private void makeClientRestOfActions(BufferedReader reader)  {
        Map<Integer, String> availableActions = new HashMap<>();
        availableActions.put(1, "createConversation");
        availableActions.put(2, "listConversations");
        availableActions.put(3, "getConversationMessages");
        availableActions.put(4, "message");
        availableActions.put(5, "listUsers");

        var stopLoop = true;
        while (stopLoop) {
            try {
                System.out.println("""
                        Dostępne opcje:
                        1.Utworzenie konwersacji
                        2.Wylistowanie listy konwersacji
                        3.Wyświtlenie wiadomości w wybranej konwersacji
                        4.Wysłanie wiadomości w wybranej konwersacji
                        5.Wyświetlanie dostępnych użytkowników
                        Twój wybór:\s""");

                var input = Integer.parseInt(reader.readLine());
                performAction(availableActions.get(input));
            } catch (CommunicationException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
}
