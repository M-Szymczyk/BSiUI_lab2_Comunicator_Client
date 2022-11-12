package pl.edu.pwr.bezp.communicator2.actions.body;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class TerminalInputBodyCreator implements BodyCreator {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public LoginBody fillLoginBody() {
        try {
            String login, password;
            System.out.print("Wprowadz login: ");
            login = reader.readLine();
            System.out.print("\nWprowadz haslo: ");
            password = reader.readLine();
            return new LoginBody(login, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ConversationBody fillConversationCreatorBody(List<String> availableUsersName) {
        try {
            ConversationNameBody name = fillConversationNameBody();
            String[] users;
            System.out.print("\nWprowadz nazwe uzytkownika: ");
            users = new String[]{reader.readLine()};
            System.out.println();
            return new ConversationBody(name,users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ConversationNameBody fillConversationNameBody() {
        try {
            String conversation;
            System.out.print("Wprowadz nazwe konwersacji: ");
            conversation = reader.readLine();
            System.out.println();
            return new ConversationNameBody(conversation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MessageBody fillMessageBody(List<String> availableConversations) {
        try {
            String conversation, content;
            System.out.print("Wprowadz nazwe konwersacji: ");
            conversation = reader.readLine();
            System.out.print("\nWprowadz wiadomosc: ");
            content = reader.readLine();
            //todo dodac listowanie konwersacji wybor z ktorej z istniejacych konwersacji
            return new MessageBody(conversation,content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
